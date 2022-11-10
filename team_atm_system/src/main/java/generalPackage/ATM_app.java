package generalPackage;

import generalPackage.UIActions.adminOperationsUI.*;
import generalPackage.UIActions.usersOperationsUI.DecreaseBalanceServiceUIAction;
import generalPackage.UIActions.usersOperationsUI.ExitServiceUIAction;
import generalPackage.UIActions.usersOperationsUI.IncreaseBalanceServiceUIAction;
import generalPackage.UIActions.usersOperationsUI.PrintBalanceServiceUIAction;
import generalPackage.config.AtmAppConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class ATM_app {

private static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AtmAppConfiguration.class);

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
                executeAdminModule();
                break;
            }
            case 2: {
                executeUserModule();
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

    //
//    Admin Module:
//
    public static void executeAdminModule() {
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
        System.out.println("5. Find accounts by name");
        System.out.println("6. Exit");
        System.out.println();
    }

    private static void executeAdminMenuItem(int menuItem) {
        switch (menuItem) {
            case 1: {
                AddAccountAdminUIAction uiAction = applicationContext.getBean(AddAccountAdminUIAction.class);
                uiAction.execute();
                break;
            }
            case 2: {
                DeleteAccountAdminUIAction uiAction = applicationContext.getBean(DeleteAccountAdminUIAction.class);
                uiAction.execute();
                break;
            }
            case 3: {
                FindUserAdminUIAction uiAction = applicationContext.getBean(FindUserAdminUIAction.class);
                uiAction.execute();
                break;
            }
            case 4: {
                GetAllAccountsAdminUIAction uiAction = applicationContext.getBean(GetAllAccountsAdminUIAction.class);
                uiAction.execute();
                break;
            }
            case 5: {
                SearchAccountsAdminUIAction uiAction = applicationContext.getBean(SearchAccountsAdminUIAction.class);
                uiAction.execute();
                break;
            }
            case 6: {
                ExitAdminUIAction uiAction = applicationContext.getBean(ExitAdminUIAction.class);
                uiAction.execute();
                break;
            }
        }
    }

//
//    Users module:
//

    public static void executeUserModule() {
        while (true) {
            printUserOperationsMenu();
            int menuItem = getUserSelection();
            executeUserOperationMenuItem(menuItem);
        }

    }

    private static void printUserOperationsMenu() {
        System.out.println();
        System.out.println("Please make your choice:");
        System.out.println("1. Withdraw");
        System.out.println("2. Fulfill account");
        System.out.println("3. Print balance");
        System.out.println("4. Exit");
        System.out.println();
    }

    private static void executeUserOperationMenuItem(int menuItem) {
        switch (menuItem) {
            case 1: {
                DecreaseBalanceServiceUIAction uiAction = applicationContext.getBean(DecreaseBalanceServiceUIAction.class);
                uiAction.execute();
                break;
            }
            case 2: {
                IncreaseBalanceServiceUIAction uiAction = applicationContext.getBean(IncreaseBalanceServiceUIAction.class);
                uiAction.execute();
                break;
            }
            case 3: {
                PrintBalanceServiceUIAction uiAction = applicationContext.getBean(PrintBalanceServiceUIAction.class);
                uiAction.execute();
                break;
            }
            case 4: {
                ExitServiceUIAction uiAction = applicationContext.getBean(ExitServiceUIAction.class);
                uiAction.execute();
                break;
            }
        }
    }
}
