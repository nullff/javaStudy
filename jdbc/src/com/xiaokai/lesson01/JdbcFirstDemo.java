package com.xiaokai.lesson01;

import com.mysql.jdbc.Driver;

import java.sql.*;

public class JdbcFirstDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.加载驱动
        Class.forName("com.mysql.jdbc.Driver");

        //2.获取与数据库的连接
        String url = "jdbc:mysql://localhost:3306/jdbcStudy?useUnicode=true&characterEncoding=utf8&useSSL=true";
        String user = "root";
        String pwd = "123456";

        Connection connection = DriverManager.getConnection(url, user, pwd);

        //3.创建statement对象用于操作sql
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM `users`;";
        ResultSet resultSet = statement.executeQuery(sql);

        //4.获取结果集并解析输出
        while (resultSet.next()){
            System.out.println("id="+resultSet.getObject("id") + "\t"
                    + "name="+resultSet.getObject("NAME") + "\t"
                    + "password="+resultSet.getObject("PASSWORD") + "\t"
                    + "email="+resultSet.getObject("email") + "\t"
                    + "birth="+resultSet.getObject("birthday") + "\n"
            );
        }
        //5.关闭链接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
