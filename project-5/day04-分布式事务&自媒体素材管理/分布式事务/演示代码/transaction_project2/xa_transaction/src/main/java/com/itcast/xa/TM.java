package com.itcast.xa;

import com.mysql.jdbc.ConnectionImpl;
import com.mysql.jdbc.jdbc2.optional.MysqlXAConnection;
import com.mysql.jdbc.jdbc2.optional.MysqlXid;

import javax.sql.XAConnection;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * 事务协调器代码:
 *  控制多个数据源的 1阶段事务预提交
 *  全部提交成功后
 *  控制多个数据源的 2阶段commit提交
 *  如果中间出现任何问题
 *  出发多个数据源的 全部rollback操作
 */
public class TM { // 事务
    public void execute(Connection zfbConn,Connection yebConn) throws SQLException {
        // 是否打印XA的事务日志
        boolean logXaConmands = true;
        // 获取RM1的接口实例
        XAConnection xaConnection = new MysqlXAConnection((ConnectionImpl)zfbConn,logXaConmands);
        XAResource rm1 = xaConnection.getXAResource();
        // 获取RM2的接口实例
        XAConnection xaConnection2 = new MysqlXAConnection((ConnectionImpl)yebConn,logXaConmands);
        XAResource rm2 = xaConnection2.getXAResource();
        // 生成一个全局事务ID
        byte[] globalid = "global_id_1234567".getBytes(); // 全局事务ID
        int formateId = 1;
        // 全局事务ID
            // 分支事务ID
            // 分支事务ID
        Xid xid1 =null;
        Xid xid2 =null;
        try {
            /************************第一阶段-********事务准备**************************/
            // TM 把rmi的事务分支ID，注册到全局ID进行注册
            byte[] bqual1 = "xa00010".getBytes();
            xid1 = new MysqlXid(globalid,bqual1,formateId);
            // 事务开启-rm1的本地事务
            rm1.start(xid1,XAResource.TMNOFLAGS);
            // 支付宝 扣款
            String sql = "update t_account set amount = amount - 100 where username = 'xiaoming'";
            // 执行sql
            PreparedStatement preparedStatement = zfbConn.prepareStatement(sql);
            preparedStatement.execute();
            // 事务结束rm1
            rm1.end(xid1,XAResource.TMSUCCESS);
            // TM 把rm2的事务分支ID，注册到全局ID进行注册
            byte[] bqual2 = "xa00020".getBytes();
            xid2 = new MysqlXid(globalid,bqual2,formateId);
            // 事务开启-rm2的本地事务
            rm2.start(xid2,XAResource.TMNOFLAGS);
            // 余额宝 加款
            String sql2 = "update t_account set amount = amount + 100 where username = 'xiaoming'";
            // 执行sql
            PreparedStatement preparedStatement2 = yebConn.prepareStatement(sql2);
            preparedStatement2.execute();
            // 事务结束rm2结束
            rm2.end(xid2,XAResource.TMSUCCESS);
            // 预提交提交
            int prepare1 = rm1.prepare(xid1);
            int prepare2 = rm2.prepare(xid2);
            /************************第一阶段- end ****************************************/

            /************************第二阶段-*******事务提交*******************************/
            if(prepare1 == XAResource.XA_OK && prepare2 == XAResource.XA_OK){
                boolean onePharse = false;
                rm1.commit(xid1,onePharse);//提交事务   正式提交事务
                rm2.commit(xid2,onePharse);//提交事务
            }else{
                rm1.rollback(xid1);//提交事务
                rm2.rollback(xid2);//提交事务
            }
        }catch (Exception ex){
            try {
                if(rm1!=null && xid1!=null){
                    rm1.rollback(xid1);//提交事务
                }
                if(rm2!=null && xid2!=null){
                    rm2.rollback(xid2);//提交事务
                }
            }catch ( Exception e){
                e.printStackTrace();
            }
            // 事务回滚
            ex.printStackTrace();
        }
    }
}
