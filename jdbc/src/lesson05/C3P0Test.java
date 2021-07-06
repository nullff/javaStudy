package lesson05;

import lesson05.utils.JdbcUtils_C3P0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class C3P0Test {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtils_C3P0.getConnection();
            String sql = "select * from users;";
            preparedStatement = connection.prepareStatement(sql);
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
            JdbcUtils_C3P0.release(connection,preparedStatement,resultSet);
        }
    }

}

