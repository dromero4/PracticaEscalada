package dao.mysql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection_example {
    //Open connection
    private static Connection openConnection(String url){
        Connection connection = null;

        String user = ""; //your db username
        String password = ""; //your db password

        try {
            connection = DriverManager.getConnection(url, user, password);  //get connection by the url
        } catch (SQLException error){
            System.out.println(error.getMessage()); //catch error if connection fails
        }

        return connection; //return connection in case of success
    }

    //Close connection
    private static void closeConnection(Connection connection){
        try {
            if (connection != null) connection.close();
        } catch (SQLException error){
            System.out.println(error.getMessage());
        }
    }
}
