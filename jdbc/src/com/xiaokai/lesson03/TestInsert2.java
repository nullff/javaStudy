package com.xiaokai.lesson03;

import com.xiaokai.lesson03.utils.JdbcUtils2;

import java.sql.*;

public class TestInsert2 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtils2.getConnection();
            String sql = "insert into users (id,`name`,`password`,`email`,`birthday`) values(?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,5);
            preparedStatement.setString(2,"zzzzz");
            preparedStatement.setString(3,"111111");
            preparedStatement.setString(4,"ababab@123.com");
            preparedStatement.setDate(5,new java.sql.Date(new java.util.Date().getTime()));

            int num = preparedStatement.executeUpdate();
            if (num > 0){
                System.out.println("插入成功！");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils2.release(connection,preparedStatement,resultSet);
        }
    }
}
