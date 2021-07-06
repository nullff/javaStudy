package com.xiaokai.lesson03;

import com.xiaokai.lesson03.utils.JdbcUtils2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDelete2 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtils2.getConnection();
            String sql = "delete from users where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,5);

            int num = preparedStatement.executeUpdate();
            if (num > 0){
                System.out.println("删除成功！");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils2.release(connection,preparedStatement,resultSet);
        }
    }
}
