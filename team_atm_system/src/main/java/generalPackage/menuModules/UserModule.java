package generalPackage.menuModules;


import generalPackage.dependencyInjection.ApplicationContext;
import generalPackage.usersOperationsUI.DecreaseBalanceServiceUIAction;
import generalPackage.usersOperationsUI.ExitServiceUIAction;
import generalPackage.usersOperationsUI.IncreaseBalanceServiceUIAction;
import generalPackage.usersOperationsUI.PrintBalanceServiceUIAction;

import java.util.Scanner;


public class UserModule {

        private static ApplicationContext applicationContext = new ApplicationContext();
//    private static ApplicationContext applicationContext = new DIApplicationContextBuilder().build("generalPackage");

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
