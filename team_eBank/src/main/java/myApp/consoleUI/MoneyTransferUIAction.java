package myApp.consoleUI;

import myApp.core.requests.MoneyTransferRequest;
import myApp.core.responses.MoneyTransferResponse;
import myApp.core.services.MoneyTransferService;
import myApp.core.services.authentication.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

//@Component
public class MoneyTransferUIAction implements UIAction {
    @Autowired
    private MoneyTransferService service;
    @Autowired
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

