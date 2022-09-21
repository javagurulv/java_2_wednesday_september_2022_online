package myApp.consoleUI;

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
        System.out.println("Enter your personal code: ");
        String personalCode = scanner.nextLine();
        System.out.println("Enter another personal code");
        String anotherPersonalCode = scanner.nextLine() ;
        System.out.println("Enter value: ");
        int value = scanner.nextInt();
        MoneyTransferRequest request = new MoneyTransferRequest(personalCode, anotherPersonalCode, value);
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
