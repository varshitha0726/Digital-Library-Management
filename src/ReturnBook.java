import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.Date;

public class ReturnBook {
    private String username;

    public ReturnBook(String username) {
        this.username = username;
    }

    public void returnBook() throws SQLException {
        try (Connection connection = ConnectionToDb.connect()) {
            System.out.println("Books borrowed by you\n");

            // displays all the books that are borrowed by the current user
            String selectquery = "SELECT bookname,issued_on FROM lending WHERE username = ? ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectquery)) {
                preparedStatement.setString(1, username);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    System.out.printf("%-50s %-50s\n ",
                            "bookname", "issued_on");
                    while (resultSet.next()) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String formattedDate = dateFormat.format(resultSet.getDate(2));
                        System.out.printf("%-50s %s \n",
                                resultSet.getString(1),
                                formattedDate);
                    }
                }
            }

            System.out.println("Enter the bookname that you want to return");
            String book = Input.getStr();
            String dateQuery = "SELECT due_date FROM lending WHERE bookname = ? AND username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(dateQuery)) {
                preparedStatement.setString(1, book);
                preparedStatement.setString(2, username);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {

                    Date dueDate = resultSet.getDate(1);
                    LocalDate due = dueDate.toLocalDate();
                    LocalDate currDate = LocalDate.now();

                    if (currDate.isAfter(due)) {
                        Fine fine = new Fine(due, currDate);
                        long fineToBePaid = fine.fine();
                        System.out.println("Pay the fine of rupees " + fineToBePaid);
                    }
                }
                System.out.println("Outstanding fine amount is cleared and the book is returned");
            }
            // deleting the borrowed record from table lending
            String delQuery = "DELETE FROM lending WHERE bookname =? AND username= ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(delQuery)) {
                preparedStatement.setString(1, book);
                preparedStatement.setString(2, username);
                int rowsUpdated = preparedStatement.executeUpdate();

            // updating the quantity of the book in books
            String updateQuery = " UPDATE books SET quantity = quantity + ? WHERE bookname = ?";
            try (PreparedStatement preparedStatement1 = connection.prepareStatement(updateQuery)) {
                preparedStatement1.setInt(1, rowsUpdated);
                preparedStatement1.setString(2, book);
                preparedStatement1.execute();
            }
        }
        }
    }
}
