package lv.javaguru.java2.atmapp;

import lv.javaguru.java2.atmapp.database.AccountDatabaseImpl;
import lv.javaguru.java2.atmapp.database.Database;
import lv.javaguru.java2.atmapp.menuModules.AdminModule;
import lv.javaguru.java2.atmapp.menuModules.UserModule;

import java.util.Scanner;

public class ATM_app {

    private static Database database = new AccountDatabaseImpl();

    private static AdminModule adminModule = new AdminModule();
    private static UserModule userModule = new UserModule();


    public static void main(String[] args) {

        while (true) {
            printOperationChoice();
            int menuItem = getUserSelection();
            executeStartingChoice(menuItem);
        }
    }

    private static void printOperationChoice() {
        System.out.println();
        System.out.println("Please choose type of operation:");
        System.out.println("1. Admin operations");
        System.out.println("2. User Operations");
        System.out.println("3. Exit");
    }

    private static void executeStartingChoice(int menuItem) {
        switch (menuItem) {
            case 1: {
                adminModule.executeAdminModule();
                break;
            }
            case 2: {
                userModule.executeUserModule();
            }
            case 3: {
                System.out.println("Good bye!");
                System.exit(0);
            }
        }
    }


    private static int getUserSelection() {
        System.out.println("Enter menu item number to proceed: ");
        Scanner scanner = new Scanner(System.in);

        return scanner.nextInt();
    }

}
