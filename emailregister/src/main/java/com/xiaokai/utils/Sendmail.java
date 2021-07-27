package com.xiaokai.utils;

import com.sun.mail.util.MailSSLSocketFactory;
import com.xiaokai.pojo.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

//网站3秒原则：用户体验
//多线程实现用户体验：异步操作
public class Sendmail extends Thread{
    //用于给用户发送邮件的邮箱
    private String from = "xiaokainull@foxmail.com";
    //邮箱的用户名
    private String username = "xiaokainull@foxmail.com";
    //邮箱密码
    private String password = "hycpidvipkymdeja";
    //发送邮件的服务器地址
    private String host = "smtp.qq.com";

    private User user;
    public Sendmail(User user){
        this.user = user;
    }

    @Override
    public void run() {
        try{
        Properties prop = new Properties();
        prop.setProperty("mail.host","smtp.qq.com"); //设置qq邮件服务器
        prop.setProperty("mail.transport.protocol","smtp"); //邮件发送协议
        prop.setProperty("mail.smtp.auth","true"); //需要验证用户名和密码

        //关于QQ邮箱，还需要设置SSL加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable","true");
        prop.put("mail.smtp.ssl.socketFactory","sf");

        //使用JavaMail发送邮件的5个步骤
        //1.创建定义整个程序所需的环境信息的Session对象

        //QQ才有的监听，其他邮箱不用
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //发件人的邮箱用户名和授权码
                return new PasswordAuthentication("xiaokainull@foxmail.com","hycpidvipkymdeja");
            }
        });
        //开启Session的debug模式，可以看到程序发送Email的运行状态
        session.setDebug(true);

        //2.通过Session得到Transport对象
        Transport ts = session.getTransport();

        //3.使用邮箱用户名和授权码链接邮件服务器
        ts.connect("smtp.qq.com","xiaokainull@foxmail.com","hycpidvipkymdeja");

        //4.创建邮件
        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(from));//指明发送邮件的人
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(user.getEmail()));//指明收件人
        message.setSubject("用户注册邮件");//邮件主题
        String info = "恭喜你注册成功，您的用户名："+user.getUsername()+","+"您的密码："+user.getPassword();
        message.setContent("<h3 style='color: red'>"+info+"</h3>","text/html;charset=utf-8");

        //5.发送邮件
        ts.sendMessage(message,message.getAllRecipients());

        //6.关闭连接
        ts.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
