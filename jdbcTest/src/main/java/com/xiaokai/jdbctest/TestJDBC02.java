package com.xiaokai.jdbctest;

import java.sql.*;

public class TestJDBC02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "123456";

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);

        String sql = "insert into users(id, NAME, PASSWORD, email, birthday) VALUES (?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 6);
        preparedStatement.setString(2, "sasaas");
        preparedStatement.setString(3, "2131213");
        preparedStatement.setString(4, "23312@wew.com");
        preparedStatement.setDate(5, new Date(new java.util.Date().getTime()));

        int x = preparedStatement.executeUpdate();
        if (x > 0) {
            System.out.println("插入成功");
        }

        preparedStatement.close();
        connection.close();
    }
}
