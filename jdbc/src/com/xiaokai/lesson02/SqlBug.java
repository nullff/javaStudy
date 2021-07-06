package com.xiaokai.lesson02;

import com.xiaokai.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlBug {
    public static void main(String[] args) {
        SqlBug sqlBug = new SqlBug();
        sqlBug.login("xiaokai","123456");
        System.out.println("==================================");
        //利用sql拼接，输入非法sql语句，造成sql短路，窃取数据库信息；
        sqlBug.login("' or '1=1","' or '1=1");
    }
    private void login(String username, String password){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtils.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM `users` WHERE `NAME`='"+username+"' AND `PASSWORD`='"+password+"';";
            resultSet = statement.executeQuery(sql);
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
            JdbcUtils.release(connection,statement,resultSet);
        }
    }
}
