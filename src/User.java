import java.sql.SQLException;

public class User {
       private String username;

       public User(String s) {
              username = s;
              System.out.println("Welcome " + s);
       }

       public void user() throws SQLException {
              System.out.println("1.All Books");
              System.out.println("2.Categories");
              System.out.println("3.Search");
              System.out.println("4.Borrow");
              System.out.println("5:Return");
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
                                          System.out.println("\t\t\t\t\t********* Borrow Window *********");
                                          Borrow borrow = new Borrow(username);
                                          borrow.borrow();
                                          break;
                                   case 5:
                                          System.out.println("\t\t\t\t\t******** Return Window *********");
                                          ReturnBook returnBook = new ReturnBook(username);
                                          returnBook.returnBook();
                                          break;
                            }
                     } else
                            break;
              }
       }
}
