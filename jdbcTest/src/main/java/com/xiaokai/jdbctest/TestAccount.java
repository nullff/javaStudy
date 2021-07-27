package com.xiaokai.jdbctest;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ConcurrentModificationException;

public class TestAccount {
    @Test
    public void test(){
        String url = "jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "123456";

        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                connection = DriverManager.getConnection(url, username, password);
                connection.setAutoCommit(false);
                String sql1 = "update account set money = money - 100 where NAME = 'A'";
                String sql2 = "update account set money = money + 100 where NAME = 'B'";
                connection.prepareStatement(sql1).executeUpdate();
                connection.prepareStatement(sql2).executeUpdate();
                connection.commit();
                System.out.println("success");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
