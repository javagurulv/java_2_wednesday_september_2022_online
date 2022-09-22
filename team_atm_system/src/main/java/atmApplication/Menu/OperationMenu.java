package atmApplication.Menu;

import atmApplication.Menu.InterfaceActions.Balance;
import atmApplication.Menu.InterfaceActions.Deposit;
import atmApplication.Menu.InterfaceActions.UI_Menu;
import atmApplication.Menu.InterfaceActions.Withdraw;

import java.util.Scanner;

public class OperationMenu {
    static private PrintOperations printOperations = new PrintOperations();
    static private UI_Menu withdrawMenu = new Withdraw();
    static private UI_Menu depositMenu = new Deposit();
    static private Transfer transfer = new Transfer();
    static private UI_Menu balance = new Balance();
    static private Exit exit = new Exit();


    public int operationMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printOperations.printOperations();

            int userInput = scanner.nextInt();
            switch (userInput) {
                case 1 -> {
                    withdrawMenu.execute();
                }
                case 2 -> {
                    depositMenu.execute();
                }
                case 3 -> {
                    transfer.transfer();
                }
                case 4 -> {
                    balance.execute();
                }
                case 5 -> {
                    exit.exit();
                }
            }
        }
    }
}

