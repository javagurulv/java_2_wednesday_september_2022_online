package generalPackage.menuModules;

import generalPackage.ApplicationContext;
import generalPackage.core.database.AccountDatabaseImpl;
import generalPackage.core.database.UserInfo;
import generalPackage.usersOperationsUI.*;

import java.util.Scanner;

public class UserModule {
    private static ApplicationContext applicationContext = new ApplicationContext();
    private static UserInfo userInfo = new UserInfo();
    private static AccountDatabaseImpl accountDatabase = new AccountDatabaseImpl();

    public void executeUserModule() {

        UserInfo.userLogIn();
        startProgram();

//        userIdCheck();

    }

    /*
    public static void userIdCheck() {
        if (accountDatabase.userIdIsExist(userInfo.getUserId())) {
            startProgram();
        } else if (!accountDatabase.userIdIsExist(userInfo.getUserId())) {
            nonExist();
        }
    }
     */

    public static void startProgram() {
        while (true) {
            printUserOperationsMenu();
            int menuItem = getUserSelection();
            executeUserOperationMenuItem(menuItem);
        }
    }

//    public static void nonExist() {
//        System.out.println("User id not registered, try again.");
//    }

    private static void printUserOperationsMenu() {
        System.out.println();
        System.out.println("Please make your choice:");
        System.out.println("1. Withdraw");
        System.out.println("2. Fulfill account");
        System.out.println("3. Print balance");
        System.out.println("4. Return to main menu ");
        System.out.println("5. Exit");
        System.out.println();
    }

    private static int getUserSelection() {
        System.out.println("Enter menu item number to proceed: ");
        Scanner scanner = new Scanner(System.in);

        return scanner.nextInt();
    }


    private static void executeUserOperationMenuItem(int menuItem) {
        switch (menuItem) {
            case 1: {
                DecreaseBalanceServiceUIAction uiAction = applicationContext.getBean(DecreaseBalanceServiceUIAction.class);
//                withdrawUIAction.execute();
                uiAction.execute();
                break;
            }
            case 2: {
                IncreaseBalanceServiceUIAction uiAction = applicationContext.getBean(IncreaseBalanceServiceUIAction.class);
                uiAction.execute();
//                depositUIAction.execute();
                break;
            }
            case 3: {
                PrintBalanceServiceUIAction uiAction = applicationContext.getBean(PrintBalanceServiceUIAction.class);
//                printBalanceUIAction.execute();
                uiAction.execute();
                break;
            }
            case 4: {
                MainMenuUIAction uiAction = applicationContext.getBean(MainMenuUIAction.class);
                uiAction.execute();
                break;
            }
            case 5: {
                ExitServiceUIAction uiAction = applicationContext.getBean(ExitServiceUIAction.class);
                uiAction.execute();
//                exitUIAction.execute();
                break;
            }
        }
    }
}
