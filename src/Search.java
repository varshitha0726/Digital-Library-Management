import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Search {
    public boolean search() throws SQLException {
        Boolean check = false;
        try {
            Connection connection = ConnectionToDb.connect();
            String bookname = Input.getStr();
            String query = "SELECT bookname,author,edition,quantity from books where bookname = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, bookname);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.printf("%-50s %-50s %-10s \n\n",
                    "Book name", "Author", "Edition");
            while (resultSet.next()) {
                if (resultSet.getInt("quantity") != 0) {
                    System.out.printf("%-50s %-50s %-10s \n",
                            resultSet.getString("bookname"),
                            resultSet.getString("author"),
                            resultSet.getString("edition"));
                    check = true;
                }
            }
            if (!check) {
                System.out.printf("%-50s %-50s %-10s\n",
                        "-", "-", "-");
            }
        } catch (Exception e) {
            System.out.println("Try again");
        }
        return check;
    }
}
