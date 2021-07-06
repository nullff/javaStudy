package com.xiaokai.lesson03;

import com.xiaokai.lesson03.utils.JdbcUtils2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestUpdate2 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtils2.getConnection();
            String sql = "SELECT * FROM `users`;";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()){
                System.out.println("id="+resultSet.getObject("id") + "\t"
                        + "name="+resultSet.getObject("NAME") + "\t"
                        + "password="+resultSet.getObject("PASSWORD") + "\t"
                        + "email="+resultSet.getObject("email") + "\t"
                        + "birth="+resultSet.getObject("birthday") + "\n"
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils2.release(connection,preparedStatement,resultSet);
        }
    }
}
