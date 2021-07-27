package com.xiaokai.jdbctest;

import java.sql.*;

public class TestJDBC01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "123456";

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();

        String sql = "select * from users";
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            System.out.print("name="+resultSet.getObject("NAME")+"\t");
            System.out.print("password="+resultSet.getObject("PASSWORD")+"\t");
            System.out.print("email="+resultSet.getObject("email")+"\t");
            System.out.println("birthday="+resultSet.getObject("birthday"));

        }

        resultSet.close();
        statement.close();
        connection.close();

    }
}
