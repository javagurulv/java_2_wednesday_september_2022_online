package atmApplication.Menu;

import atmApplication.Database.Database;

import java.util.Scanner;

public class AccountCheck {
    private static OperationMenu operationMenu = new OperationMenu();
    private Database database;

    public boolean accountCheck() {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        if (!database.isExist(name)) {
            System.out.println("Incorrect Name.");
        }
        return database.isExist(name);
    }


}


