package generalPackage.core.database;

import java.util.Scanner;



public class UserInfo {
    private static int userId;

    public int getUserId() {
        return userId;
    }

    public static void userLogIn() {
        System.out.println("Hello, please, input your id : ");
        Scanner scanner = new Scanner(System.in);

        userId = scanner.nextInt();
    }
}
