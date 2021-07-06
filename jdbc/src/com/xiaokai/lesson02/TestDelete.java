package com.xiaokai.lesson02;

import com.xiaokai.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDelete {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtils.getConnection();
            statement = connection.createStatement();
            String sql = "DELETE FROM users WHERE id = 5;";
            int num = statement.executeUpdate(sql);
            if (num > 0){
                System.out.println("删除成功！");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.release(connection,statement,resultSet);
        }
    }
}
