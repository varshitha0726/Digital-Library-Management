import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    String username;
    String userPassword;

    Login() {
        System.out.println("Enter Username ");
        username = Input.getString();
        System.out.println("\nEnter Password: ");
        userPassword = Input.getString();

    }

    public int login() throws SQLException {
        if (username.equals("Admin") && userPassword.equals("Book@123")) {
            return 1;
        }
        try (Connection connection = ConnectionToDb.connect()) {
            String selectQuery = "SELECT * FROM userdb WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, username);
                try (ResultSet resultset = preparedStatement.executeQuery()) {
                    if (resultset.next()) {
                        String password = resultset.getString(2);
                        if (userPassword.equals(password))
                            return 0;
                        else
                            return -1;
                    }
                }
            }
        }
        return -1;
    }

    public String getuser() {
        return username;
    }
}