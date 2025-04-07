package dao.mysql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {
    private static Connection openConnection(String url){
        Connection connection = null;

        String user = "root";
        String password = "";

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException error){
            System.out.println(error.getMessage());
        }

        return connection;
    }

    private static void closeConnection(Connection connection){
        try {
            if (connection != null) connection.close();
        } catch (SQLException error){
            System.out.println(error.getMessage());
        }
    }
}
