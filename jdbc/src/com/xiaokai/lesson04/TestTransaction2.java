package com.xiaokai.lesson04;

import com.xiaokai.lesson03.utils.JdbcUtils2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestTransaction2 {
    public static void main(String[] args) {
        TestTransaction2 transaction2 = new TestTransaction2();
        transaction2.testTransfer2("A","B");
    }

    private void testTransfer2(String A, String B){
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

            int x = 1/0; //模拟转账中的错误

            String sql2 = "update account set money=money+100 where `name`= ?;";
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setString(1,B);
            preparedStatement.executeUpdate();

            connection.commit();//提交事务
            System.out.println("转账成功！");


        } catch (Exception throwables) {
            throwables.printStackTrace();
            System.out.println("转账失败！");
            try {
                connection.rollback();//显示定义回滚，默认也会回滚，是隐式；
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }finally {
            JdbcUtils2.release(connection,preparedStatement,resultSet);
        }
    }
}
