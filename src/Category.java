import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Category {
    public void category() throws SQLException {
        try {
            Connection connection = ConnectionToDb.connect();
            String query = "SELECT category FROM books GROUP BY category";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("\t\t\t\t\t**********  Categories  ***********\n");
            int x = 1;
            while (resultSet.next()) {
                String category = resultSet.getString("category");
                System.out.print(x + ".");
                System.out.println(category);
                x++;
            }
            System.out.println("\nEnter the category you want to view");
            String cat = Input.getStr();
            query = "SELECT bookname,author,edition,quantity from books where category = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cat);
            resultSet = preparedStatement.executeQuery();
            System.out.printf("%-50s %-50s %-10s \n\n",
                    "Book name", "Author", "Edition");
            boolean check = false;
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
                System.out.println("No books available in " + cat);
            }
        } catch (Exception e) {
            System.out.println("Please try again later");
        }
    }

}
