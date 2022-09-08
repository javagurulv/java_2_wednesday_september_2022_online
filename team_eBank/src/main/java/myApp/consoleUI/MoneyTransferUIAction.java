package myApp.consoleUI;

import myApp.database.DataBase;
import myApp.services.MoneyTransferService;

import java.util.Scanner;

public class MoneyTransferUIAction implements UIAction {

    private MoneyTransferService service;
    public MoneyTransferUIAction(DataBase dataBase) {
        service = new MoneyTransferService(dataBase);
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your ID: ");
        Long accountID = scanner.nextLong();
        System.out.println("Enter amount: ");
        int amount = scanner.nextInt();
        System.out.println("Enter the account id to whom you want to make a transfer: ");
        Long anotherAccountID = scanner.nextLong();
        service.execute(accountID, amount, anotherAccountID);
        System.out.println("Transaction has been made");
    }

}
