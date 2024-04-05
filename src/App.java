public class App {
    public static void main(String args[]) throws Exception {
        System.out.println("\n\n\t\t\t\t**************   Digital Library Management   ************\n");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("Press 0 to exit\n");
        System.out.println("Select an Option");
        int choice;
        while (true) {
            choice = Input.getInt();
            if (choice == 1 || choice == 2) {
                switch (choice) {
                    case 1:
                        System.out.println("\nYou selected Register");
                        Registration register = new Registration();
                        register.register();
                        break;
                    case 2:
                        System.out.println("\nYou selected Login");
                        Login login = new Login();
                        int x = login.login();
                        String username = login.getuser();
                        if (x == 1) {
                            System.out.println("\nAdmin Login Successful");
                            Admin admin = new Admin();
                            admin.admin();
                        } else if (x == 0) {
                            System.out.println("\nUser Login Successful\n");
                            User user = new User(username);
                            user.user();
                        } else
                            System.out.println("\nIncorrect login id or password");
                        break;

                    default:
                        System.out.println("\nInvalid choice.");
                }
            } else
                break;
            System.out.println("\nSelect an option");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("Press 0 to exit\n");
        }
    }
}