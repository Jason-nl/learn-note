package com.itheima.health.entity;

import lombok.Data;

;import java.io.Serializable;

@Data
public class EmailEntity  implements Serializable {

    private String  subject = "传智健康官方邮件";
    private String  content;
    private String  emailTo="tps520tps@163.com";
    private String  emailFrom="tps520wx@163.com";

}
