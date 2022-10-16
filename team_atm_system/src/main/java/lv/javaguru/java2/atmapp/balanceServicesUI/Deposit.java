package lv.javaguru.java2.atmapp.balanceServicesUI;

import lv.javaguru.java2.atmapp.balanceServices.IncreaseBalance;

import java.util.Scanner;

public class Deposit implements UI_Menu {

    private IncreaseBalance increaseBalance;

    public Deposit(IncreaseBalance increaseBalance) {
        this.increaseBalance = increaseBalance;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please confirm your ID: ");
        int userID = scanner.nextInt();
        System.out.print("Please, input amount for deposit : ");
        int amountToDeposit = scanner.nextInt();
        increaseBalance.execute(userID, amountToDeposit);
    }
}
