package atmApplication;

import atmApplication.Database.AccountDatabaseImpl;
import atmApplication.Database.Database;

import java.util.Scanner;

public class ConsoleInput {
    private static Database database = new AccountDatabaseImpl();

    public static void main(String[] args) {

        String name;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Input name: ");
        name = scanner.nextLine();
    }
}
