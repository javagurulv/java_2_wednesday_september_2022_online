package atmApplication;

import atmApplication.Database.AccountDatabaseImpl;
import atmApplication.Database.Database;
import atmApplication.Menu.AccountCheck;
import atmApplication.Menu.OperationMenu;

import java.util.Scanner;

public class ATM_application {

    private static AccountCheck accountCheck = new AccountCheck();
    private static OperationMenu operationMenu = new OperationMenu();
    private static Database database = new AccountDatabaseImpl();



    public static void main(String[] args) {

//
//        if (accountCheck.accountCheck()) {
//            operationMenu.operationMenu();
//        }

        Scanner scanner = new Scanner(System.in);
        String userName = scanner.nextLine();

        while (true){
            operationMenu.operationMenu();
        }
    }
}
