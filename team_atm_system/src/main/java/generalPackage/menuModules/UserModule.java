package generalPackage.menuModules;

import generalPackage.balanceOperations.DecreaseBalance;
import generalPackage.balanceOperations.IncreaseBalance;
import generalPackage.balanceOperations.PrintBalance;
import generalPackage.balanceOperationsUI.*;
import generalPackage.database.AccountDatabaseImpl;
import generalPackage.database.Database;

import java.util.Scanner;

public class UserModule {
    private static Database database = new AccountDatabaseImpl();
    private static IncreaseBalance increaseBalance = new IncreaseBalance(database);
    private static DecreaseBalance decreaseBalance = new DecreaseBalance(database);
    private static PrintBalance printBalance = new PrintBalance(database);
    private static UI_Menu printBalanceUIAction = new Balance(printBalance);
    private static UI_Menu depositUIAction = new Deposit(increaseBalance);
    private static UI_Menu withdrawUIAction = new Withdraw(decreaseBalance);
    private static UI_Menu exitUIAction = new Exit();


    public void executeUserModule (){
        while (true){
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
                withdrawUIAction.execute();
                break;
            }
            case 2: {
                depositUIAction.execute();
                break;
            }
            case 3: {
                printBalanceUIAction.execute();
                break;
            }
            case 4: {
                exitUIAction.execute();
                break;
            }
        }
    }
}
