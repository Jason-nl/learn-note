package com.itcast.xa;

import java.sql.Connection;
import java.sql.DriverManager;

public class AP {
    public Connection getRmZhiFuBaoConn(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.200.131:3306/alipay_zhifubao?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false","root","root");
            return connection;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    public Connection getRmYuEBaoConn(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.200.131:3306/alipay_yuebao?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false","root","root");
            return connection;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
