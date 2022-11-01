package lv.javaguru.java2.atmapp;

import lv.javaguru.java2.atmapp.core.database.AccountDatabaseImpl;
import lv.javaguru.java2.atmapp.core.database.Database;
import lv.javaguru.java2.atmapp.menuModules.AdminModule;
import lv.javaguru.java2.atmapp.menuModules.UserModule;

import java.util.Scanner;

public class ATM_app {

    private static Database database = new AccountDatabaseImpl();

    private static AdminModule adminModule = new AdminModule();
    private static UserModule userModule = new UserModule();


    public static void main(String[] args) {

        startApp();

    }

    public static void startApp() {
        while (true) {
            printOperationChoice();
            int menuItem = getUserSelection();
            executeStartingChoice(menuItem);
        }
    }

    private static void printOperationChoice() {
        System.out.println();
        System.out.println("Please choose type of operation:");
        System.out.println("1. User Operations");
        System.out.println("2. Exit");
    }

    private static int getUserSelection() {
        System.out.println("Enter menu item number to proceed: ");
        Scanner scanner = new Scanner(System.in);

        return scanner.nextInt();
    }

    private static void executeStartingChoice(int menuItem) {
        switch (menuItem) {
            case 1: {
                userModule.executeUserModule();
            }
            case 2: {
                System.out.println("Good bye!");
                System.exit(0);
            }
            case 11: {
                System.out.println();
                System.out.println("You successful log in as administrator");
                adminModule.executeAdminModule();
            }
        }
    }
}
