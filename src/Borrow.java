import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;

public class Borrow {
    private String username;
    public Borrow(String username)
    {
        this.username = username;
    }
    public void borrow() throws SQLException {
        System.out.println("The below books are available \n" );
        ShowBooks show = new ShowBooks();
        Boolean x = show.show();
        if(!x)
        System.out.println("Try again Later");
        else
        {
            System.out.println("\nEnter the bookname ");
            String book = Input.getStr();
            try(Connection connection = ConnectionToDb.connect()){
                //to store the borrowed data
                String InsertQuery = "INSERT INTO lending(username,bookname,issued_on,due_date) VALUES(?,?,?,?)";//query to store the borrowed data
                try(PreparedStatement preparedStatement = connection.prepareStatement(InsertQuery)){
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, book);
                    LocalDate currentDate = LocalDate.now();
                    preparedStatement.setDate(3, Date.valueOf(currentDate));
                    LocalDate dueDate = currentDate.plusMonths(1);
                    preparedStatement.setDate(4, Date.valueOf(dueDate));
                    preparedStatement.execute();
                    System.out.println("\nReturn the book on or before the date(YYYY-MM-DD): " + dueDate);
                }
                
                //to update quantity in books
                String updateQuery = "UPDATE books SET quantity = quantity-1 WHERE bookname = ?";//update qunatity in books
                try(PreparedStatement preparedStatement1 = connection.prepareStatement(updateQuery)){
                    preparedStatement1.setString(1, book);
                    preparedStatement1.execute();
                }

                
            }
            catch(Exception e){

            }
        }
    }
}