package myApp.consoleUI;

import myApp.core.requests.MoneyTransferRequest;
import myApp.core.responses.MoneyTransferResponse;
import myApp.core.services.MoneyTransferService;
import myApp.core.services.UserService;
import myApp.dependency_injection.DIComponent;
import myApp.dependency_injection.DIDependency;

import java.util.Scanner;

@DIComponent
public class MoneyTransferUIAction implements UIAction {
    @DIDependency
    private MoneyTransferService service;
    @DIDependency
    private UserService userService;
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        String yourPersonalCode = userService.getPersonalCode();
        System.out.println("Enter another personal code");
        String anotherPersonalCode = scanner.nextLine();
        System.out.println("Enter value: ");
        int value = scanner.nextInt();
        MoneyTransferRequest request = new MoneyTransferRequest(yourPersonalCode, anotherPersonalCode, value);
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
