import java.util.Scanner;

public class Input {
    public static Scanner sc = new Scanner(System.in);

    public static int getInt() {
        return sc.nextInt();
    }

    public static String getString() {
        return sc.next();
    }

    public static String getStr() {
        sc.nextLine();
        return sc.nextLine();
    }
    public static String str(){
        return sc.nextLine();
    }
}