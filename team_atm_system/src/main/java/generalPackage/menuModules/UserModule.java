package generalPackage.menuModules;

//import generalPackage.core.services.usersOperations.DecreaseBalance;
//import generalPackage.core.services.usersOperations.IncreaseBalance;
//import generalPackage.core.services.usersOperations.PrintBalance;
//import generalPackage.core.services.usersOperations.*;
//import generalPackage.core.services.usersOperations.usersValidators.DecreaseBalanceValidator;
//import generalPackage.core.services.usersOperations.usersValidators.IncreaseBalanceValidator;
//import generalPackage.core.services.usersOperations.usersValidators.PrintBalanceValidator;

import generalPackage.ApplicationContext;
import generalPackage.usersOperationsUI.DecreaseBalanceServiceUIAction;
import generalPackage.usersOperationsUI.ExitServiceUIAction;
import generalPackage.usersOperationsUI.IncreaseBalanceServiceUIAction;
import generalPackage.usersOperationsUI.PrintBalanceServiceUIAction;

import java.util.Scanner;

public class UserModule {
//    private static Database database = new AccountDatabaseImpl();
//    private static IncreaseBalanceValidator increaseBalanceValidator = new IncreaseBalanceValidator();
//    private static IncreaseBalance increaseBalance = new IncreaseBalance(database, increaseBalanceValidator);
//    private static DecreaseBalanceValidator decreaseBalanceValidator = new DecreaseBalanceValidator();
//    private static DecreaseBalance decreaseBalance = new DecreaseBalance(database, decreaseBalanceValidator );
//    private static PrintBalanceValidator printBalanceValidator = new PrintBalanceValidator();
//    private static PrintBalance printBalance = new PrintBalance(database, printBalanceValidator);
//    private static UI_Menu printBalanceUIAction = new PrintBalanceServiceUIAction(printBalance);
//    private static UI_Menu depositUIAction = new IncreaseBalanceServiceUIAction(increaseBalance);
//    private static UI_Menu withdrawUIAction = new DecreaseBalanceServiceUIAction(decreaseBalance);
//    private static UI_Menu exitUIAction = new ExitServiceUIAction();

    private static ApplicationContext applicationContext = new ApplicationContext();

    public void executeUserModule() {
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
                ExitServiceUIAction uiAction = applicationContext.getBean(ExitServiceUIAction.class);
                uiAction.execute();
//                exitUIAction.execute();
                break;
            }
        }
    }
}
