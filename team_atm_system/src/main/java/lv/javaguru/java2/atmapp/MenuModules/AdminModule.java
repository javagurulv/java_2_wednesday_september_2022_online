package lv.javaguru.java2.atmapp.MenuModules;

import lv.javaguru.java2.atmapp.AdminOperations.*;
import lv.javaguru.java2.atmapp.AdminOperationsUI.*;
import lv.javaguru.java2.atmapp.Database.AccountDatabaseImpl;
import lv.javaguru.java2.atmapp.Database.Database;

import java.util.Scanner;

public class AdminModule {


    private static Database database = new AccountDatabaseImpl();

    private static AddAccountService addAccountService = new AddAccountService(database);
    private static DeleteAccountService deleteAccountService = new DeleteAccountService(database);
    private static FindUserByIDServise findUserByIDServise = new FindUserByIDServise(database);
    private static GetAllAccountsService getAllAccountsService = new GetAllAccountsService(database);
    private static AdminUIactions addAccountUI = new AddAccountAdminUIAction(addAccountService);
    private static AdminUIactions deleteAccountUI = new DeleteAccountAdminUIAction(deleteAccountService);
    private static AdminUIactions findAccountUI = new FindUserAdminUIAction(findUserByIDServise);
    private static AdminUIactions getAllAccountsUI = new GetAllAccountsAdminUIAction(getAllAccountsService);

    private static AdminUIactions exitUIMenu = new ExitAdminUIAction();

    public void executeAdminModule (){
        while (true) {
            printAdminMenu();
            int menuItem = getUserSelection();
            executeAdminMenuItem(menuItem);

        }
    }

    private static void printAdminMenu() {
        System.out.println();
        System.out.println("Please make your choice:");
        System.out.println("1. Add account");
        System.out.println("2. Delete account");
        System.out.println("3. Find account");
        System.out.println("4. Print all accounts");
        System.out.println("5. Exit");
        System.out.println();
    }

    private static int getUserSelection() {
        System.out.println("Enter menu item number to proceed:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }


    private static void executeAdminMenuItem(int menuItem) {
        switch (menuItem) {
            case 1: {
                addAccountUI.execute();
                break;
            }
            case 2: {
                deleteAccountUI.execute();
                break;
            }
            case 3: {
                findAccountUI.execute();
                break;
            }
            case 4: {
                getAllAccountsUI.execute();
                break;
            }
            case 5: {
                exitUIMenu.execute();
                break;
            }
        }
    }
}
