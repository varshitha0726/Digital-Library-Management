import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowBooks {
    public boolean show() throws SQLException {
        boolean done = false;
        try (Connection connection = ConnectionToDb.connect()) {
            String query = "SELECT bookname,author,edition,quantity FROM books";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    System.out.printf("%-50s %-50s %-10s \n\n",
                            "Book name", "Author", "Edition");
                    done = true;
                    while (resultSet.next()) {
                        if (resultSet.getInt("quantity") != 0) {
                            System.out.printf("%-50s %-50s %-10s \n",
                                    resultSet.getString("bookname"),
                                    resultSet.getString("author"),
                                    resultSet.getString("edition"));
                        }
                    }
                }
            }
        }
        if (done)
            return true;
        else
            return false;
    }
}
