import java.sql.SQLException;

public class Registration {
    private String username;
    private String password;
    private String confirmPassword;
    private String phoneNumber;
    private String email;

    public Registration() {
        System.out.println("Welcome to Registration!");
        System.out.print("Enter username: ");
        this.username = Input.getString();

        System.out.print("Enter password: ");
        this.password = Input.getString();

        System.out.print("Confirm password: ");
        this.confirmPassword = Input.getString();

        System.out.print("Enter phone number: ");
        this.phoneNumber = Input.getString();

        System.out.print("Enter email: ");
        this.email = Input.getString();

    }

    public void register() throws SQLException {
        String[] Details = new String[] { this.username, this.password, this.phoneNumber, this.email };

        if (validate()) {
            if (!ValidateUsername.validate(this.username)) {
                System.out.println("\nRegistration failed. Username already taken.\n");
                return;
            }

            InsertIntoDb iDb = new InsertIntoDb(Details);
            int x = iDb.insert();
            if (x == 1)
                System.out.println("\nRegistration Successful!");
            else
                System.out.println("\nRegistration failed. Please try again.");
        } else {
            System.out.println("\nRegistration failed.\n");
        }
    }

    private boolean validate() {

        if (this.username.isEmpty() || this.email.isEmpty() || this.phoneNumber.isEmpty()
                || this.password.isEmpty() || this.confirmPassword.isEmpty()) {
            System.out.println("Please fill in all the fields.");
            return false;
        }

        if (!this.password.equals(this.confirmPassword)) {
            System.out.println("Password and confirm password do not match.");
            return false;
        }
        return true;
    }
}