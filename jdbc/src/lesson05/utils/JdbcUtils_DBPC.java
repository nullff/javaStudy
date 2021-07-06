package lesson05.utils;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

//DBPC连接池 JDBC数据库工具类
public class JdbcUtils_DBPC {
    private static DataSource ds = null;
    static{
        try {
            //读取配置
            InputStream inputStream = JdbcUtils_DBPC.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            //创建数据源
            ds = BasicDataSourceFactory.createDataSource(properties);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //从数据源中获取数据库连接
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    //关闭连接释放资源
    public static void release(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (resultSet != null)
                resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (preparedStatement != null)
                preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
