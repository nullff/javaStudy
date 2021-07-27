package com.xiaokai.dao;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class BaseDao {
    //静态代码块,在类加载的时候执行
    static{
        init();
    }

    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    //初始化连接参数,从配置文件里获得
    public static void init(){
        Properties params=new Properties();
        String configFile = "database.properties";
        InputStream is = BaseDao.class.getClassLoader().getResourceAsStream(configFile);
        try {
            params.load(is);
            driver=params.getProperty("driver");
            url=params.getProperty("url");
            user=params.getProperty("user");
            password=params.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //获取数据库连接
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {

            e.printStackTrace();
        }

        return connection;
    }

    //编写数据库查询公共类
    public static ResultSet execute(Connection connection, PreparedStatement preparedStatement, String sql, Object[] params, ResultSet resultSet) throws SQLException {
        //sql预编译
        preparedStatement = connection.prepareStatement(sql);
        //params参数数组是冲0开始，遍历从0开始，但是setObject的占位符index要求从1开始，所以index=i+1；
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    //编写数据库增删改工具类
    public static int executeUpdate(Connection connection, PreparedStatement preparedStatement, String sql, Object[] params) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        //params参数数组是冲0开始，遍历从0开始，但是setObject的占位符index要求从1开始，所以index=i+1；
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        int executeUpdate = preparedStatement.executeUpdate();
        return executeUpdate;
    }

    //关闭资源类
    public static boolean closeResource(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        boolean flag = true;
        if (resultSet != null) {
            try {
                resultSet.close();
                //GC垃圾回收
                resultSet = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                //发生异常，释放资源失败返回false
                flag = false;
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
                preparedStatement = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                flag = false;
            }
        }
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                flag = false;
            }
        }
        return flag;
    }
//@Test
//public void test(){
//    BaseDao baseDao = new BaseDao();
//    Connection connection = BaseDao.getConnection();
//    PreparedStatement preparedStatement = null;
//    ResultSet resultSet = null;
//    String sql = "select * from smbms_user where userCode = ?";
//    Object[] params = {"admin"};
//    try {
//        resultSet = baseDao.execute(connection,preparedStatement,sql,params,resultSet);
//    } catch (SQLException throwables) {
//        throwables.printStackTrace();
//    }
//    try {
//        System.out.println(resultSet.getString("userPassword"));
//    } catch (SQLException throwables) {
//        throwables.printStackTrace();
//    }
////}
//@Test
//    public void test2(){
//    new BaseDao();
//}


}



