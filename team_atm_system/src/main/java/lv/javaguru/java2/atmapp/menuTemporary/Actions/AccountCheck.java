package lv.javaguru.java2.atmapp.menuTemporary.Actions;

import lv.javaguru.java2.atmapp.menuTemporary.AccountsTemp;

import java.util.Scanner;

public class AccountCheck {
    static private OperationMenu operationMenu = new OperationMenu();
    private AccountsTemp account;

    public boolean accountCheck() {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        if (!account.isExist(name)) {
            System.out.println("Incorrect Name.");
        }
        return account.isExist(name);
    }
}
