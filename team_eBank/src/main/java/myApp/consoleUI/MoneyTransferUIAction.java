package myApp.consoleUI;

import myApp.core.database.DataBase;
import myApp.core.requests.MoneyTransferRequest;
import myApp.core.responses.MoneyTransferResponse;
import myApp.core.services.MoneyTransferService;

import java.util.Scanner;

public class MoneyTransferUIAction implements UIAction {

    private MoneyTransferService service;
    public MoneyTransferUIAction(MoneyTransferService service) {
        this.service = service;
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
        MoneyTransferRequest request = new MoneyTransferRequest(accountID, amount, anotherAccountID);
        MoneyTransferResponse response = service.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: "
                    + coreError.getField() + " " + coreError.getMessage()));
        } else if (response.isTransactionSucceed()) {
            System.out.println("Transaction completed successfully");
        } else {
            System.out.println("Transaction not perfect");
        }
    }

}
