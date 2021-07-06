package com.xiaokai.lesson02.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {
    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;
    //静态代码块 用于读取数据库配置信息 加载驱动
    static {
        try{
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

            Class.forName(driver);

        }catch (Exception e){
            throw new ExceptionInInitializerError(e);
        }
    }
    //链接数据库并返回connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }
    //关闭数据库连接释放资源
    public static void release(Connection connection, Statement statement, ResultSet resultSet){
        try {
            if (resultSet!=null)
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (statement!=null)
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (connection!=null)
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
