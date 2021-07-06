package lesson05.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils_C3P0 {
    private static ComboPooledDataSource ds = null;
    static{
        try {
//            //代码方式设置数据源 不推荐 高耦合
//            ds = new ComboPooledDataSource();
//            ds.setDriverClass("com.mysql.jdbc.Driver");
//            ds.setJdbcUrl("jdbc:mysql://localhost:3306/jdbcStudy");
//            ds.setUser("root");
//            ds.setPassword("123456");
//            ds.setInitialPoolSize(10);
//            ds.setMinPoolSize(5);
//            ds.setMaxPoolSize(20);

            //读取配置
            //创建数据源
            ds = new ComboPooledDataSource("MySQL");

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
