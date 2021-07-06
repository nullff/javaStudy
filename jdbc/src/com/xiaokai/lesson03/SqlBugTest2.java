package com.xiaokai.lesson03;

import com.xiaokai.lesson02.SqlBug;
import com.xiaokai.lesson02.utils.JdbcUtils;
import com.xiaokai.lesson03.utils.JdbcUtils2;

import java.sql.*;

public class SqlBugTest2 {
    public static void main(String[] args) {
        SqlBugTest2 sqlBugTest2 = new SqlBugTest2();
        sqlBugTest2.login("gggg","123456");
        System.out.println("==================================");

        sqlBugTest2.login("' or '1=1","' or '1=1");
    }
    private void login(String username, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtils2.getConnection();
            String sql = "select * from users where `NAME` = ? and `PASSWORD`=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);

            resultSet = preparedStatement.executeQuery();
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
