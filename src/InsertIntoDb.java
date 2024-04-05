import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertIntoDb {
    String username;
    String password;
    String phno;
    String email;

    InsertIntoDb(String[] details) {
        username = details[0];
        password = details[1];
        phno = details[2];
        email = details[3];
    }

    public int insert() {

        try {
            Connection connection = ConnectionToDb.connect();
            String insertQuery = "INSERT INTO userdb ( username,password,phno,email) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, phno);
            preparedStatement.setString(4, email);

            preparedStatement.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}