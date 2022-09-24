package lv.javaguru.java2.atmapp.BalanceOperationsUI;

import lv.javaguru.java2.atmapp
        .BalanceOperations.DecreaseBalance;

import java.util.Scanner;

public class Withdraw implements UI_Menu {

   private DecreaseBalance decreaseBalance;

    public Withdraw(DecreaseBalance decreaseBalance) {
        this.decreaseBalance = decreaseBalance;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please confirm your ID: ");
        int userID = scanner.nextInt();
        System.out.println("Please input amount to withdraw:");
        int amountToDecrease = scanner.nextInt();
        decreaseBalance.execute(userID,amountToDecrease);
    }



}
