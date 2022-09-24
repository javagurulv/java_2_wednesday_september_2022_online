package lv.javaguru.java2.atmapp.BalanceOperationsUI;

import lv.javaguru.java2.atmapp.BalanceOperations.PrintBalance;

import java.util.Scanner;


public class Balance implements UI_Menu {

    PrintBalance printBalance;

    public Balance(PrintBalance printBalance) {
        this.printBalance = printBalance;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please confirm your ID: ");
        int userID = scanner.nextInt();
        printBalance.execute(userID);

    }
}
