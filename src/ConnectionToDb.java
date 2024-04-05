import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDb {
    private static String Url = "jdbc:mysql://localhost:3306/dlm";
    private static String host = "root";
    private static String Password = "Varsh@263";

    public static Connection connect() throws SQLException {
        Connection connection = DriverManager.getConnection(Url, host, Password);

        return connection;
    }
}