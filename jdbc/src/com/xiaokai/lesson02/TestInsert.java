package com.xiaokai.lesson02;

import com.xiaokai.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestInsert {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtils.getConnection();
            statement = connection.createStatement();
            String sql = "INSERT INTO `users`(id,`NAME`,`PASSWORD`,`email`,birthday)" + "VALUES(5,'aaaa','123456','123@123.com','1980-12-04');";
            int num = statement.executeUpdate(sql);
            if (num > 0){
                System.out.println("插入成功！");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.release(connection,statement,resultSet);
        }
    }
}
