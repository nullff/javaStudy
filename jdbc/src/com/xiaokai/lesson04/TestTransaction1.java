package com.xiaokai.lesson04;

import com.xiaokai.lesson03.utils.JdbcUtils2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class TestTransaction1 {
    public static void main(String[] args) {
        TestTransaction1 transaction1 = new TestTransaction1();
        transaction1.testTransfer("A","B");
    }

    public void testTransfer(String A, String B){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtils2.getConnection();
            connection.setAutoCommit(false);//关闭数据库自动提交，同时自动开启事务

            String sql1 = "update account set money=money-100 where `name`= ?;";
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setString(1,A);
            preparedStatement.executeUpdate();

            String sql2 = "update account set money=money+100 where `name`= ?;";
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setString(1,B);
            preparedStatement.executeUpdate();

            connection.commit();//提交事务
            System.out.println("转账成功！");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils2.release(connection,preparedStatement,resultSet);
        }
    }
}
