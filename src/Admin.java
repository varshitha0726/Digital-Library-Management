import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Admin {
    void admin() throws SQLException {
        System.out.println("1.All Books");
        System.out.println("2.Categories");
        System.out.println("3.Search");
        System.out.println("4.Add new book");
        System.out.println("5.Delete book");
        System.out.println("6.Update quantity");
        int choice;
        while (true) {
            System.out.println("\nEnter your choice OR press 0 to exit");
            choice = Input.getInt();
            if (choice != 0) {
                switch (choice) {
                    case 1:
                        System.out.println("\t\t\t\t\t**********  Available books  ************\n");
                        ShowBooks show = new ShowBooks();
                        if (!show.show())
                            System.out.println("Try again later");
                        break;
                    case 2:
                        System.out.println("Browse by category");
                        Category cat = new Category();
                        cat.category();
                        break;
                    case 3:
                        System.out.println("\t\t\t\t\t********** Search **********");
                        System.out.println("Enter the book name");
                        Search search = new Search();
                        Boolean check = search.search();
                        if (!check)
                            System.out.println("No book found");
                        break;
                    case 4:
                        System.out.println("\t\t\t\t\t******** Add New Book *********");
                        boolean done = false;
                        try (Connection connection = ConnectionToDb.connect()) {
                            String insertQuery = "INSERT INTO books VALUES(?,?,?,?,?)";
                            System.out.println("Enter the bookname");
                            String bookname = Input.getStr();
                            System.out.println("Enter author name");
                            String author = Input.str();
                            System.out.println("Enter edition of the book");
                            String edition = Input.str();
                            System.out.println("Specify the category of the book");
                            String category = Input.str();
                            System.out.println("Enter the quantity in units");
                            int Quantity = Input.getInt();
                            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                                preparedStatement.setString(1, bookname);
                                preparedStatement.setString(2, author);
                                preparedStatement.setString(3, edition);
                                preparedStatement.setString(4, category);
                                preparedStatement.setInt(5, Quantity);
                                preparedStatement.execute();
                            }
                            done = true;
                        } catch (Exception e) {
                            System.out.println("Try again later");
                        }
                        if (done)
                            System.out.println("Book is added successfully");
                        break;
                    case 5:
                        boolean remove = false;
                        System.out.println("\t\t\t\t\t*********** Remove Book ***********");
                        try (Connection connection = ConnectionToDb.connect()) {
                            System.out.println("Enter the bookname that is to be removed ");
                            String book = Input.getStr();
                            String delQuery = "DELETE FROM books WHERE bookname=?";
                            try (PreparedStatement preparedStatement = connection.prepareStatement(delQuery)) {
                                preparedStatement.setString(1, book);
                                preparedStatement.execute();
                            }
                            remove = true;
                        }
                        if (remove)
                            System.out.println("Book is removed Successfully");
                        break;
                    case 6:
                        System.out.println("\t\t\t\t\t********* Update Book Data ************");
                        try (Connection connection = ConnectionToDb.connect()) {
                            System.out.println("Enter tha book name, whose qunatity is to be updated");
                            String book = Input.getStr();
                            System.out.println("Enter 1 to increase qunatity or\n Enter 2 to decrease qunatity");
                            int x = Input.getInt();
                            String updateQuery;
                            int update;
                            if (x == 1) {
                                System.out.println("Enter the quantity to be added");
                                update = Input.getInt();
                                updateQuery = "UPDATE books SET quantity=quantity + ? WHERE bookname = ?";
                                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                                    preparedStatement.setInt(1, update);
                                    preparedStatement.setString(2, book);
                                    preparedStatement.execute();
                                }
                            } else {
                                System.out.println("Enter the quantity to be removed");
                                update = Input.getInt();
                                updateQuery = "UPDATE books SET quantity=quantity - ? WHERE bookname = ?";
                                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                                    preparedStatement.setInt(1, update);
                                    preparedStatement.setString(2, book);
                                    preparedStatement.execute();
                                }
                            }
                        }
                        System.out.println("Update Successful");
                        break;
                }
            } else
                break;
        }
    }
}
