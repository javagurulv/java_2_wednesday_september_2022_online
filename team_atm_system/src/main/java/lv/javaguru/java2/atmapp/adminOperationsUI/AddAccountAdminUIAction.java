package lv.javaguru.java2.atmapp.adminOperationsUI;

import lv.javaguru.java2.atmapp.core.requests.adminRequests.AddAccountRequest;
import lv.javaguru.java2.atmapp.core.responses.adminResponses.AddAccountResponse;
import lv.javaguru.java2.atmapp.core.services.adminOperations.AddAccountService;

import java.util.Scanner;

public class AddAccountAdminUIAction implements AdminUIactions {

    private AddAccountService addAccountService;

    public AddAccountAdminUIAction(AddAccountService addAccountService) {
        this.addAccountService = addAccountService;
    }


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user name: ");
        String userName = scanner.nextLine();
        System.out.println("Enter user ID: ");
        int userID = scanner.nextInt();
        AddAccountRequest request = new AddAccountRequest(userName, userID);
        AddAccountResponse response = addAccountService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Registration errors were detected: " + coreError.getField() + " " + coreError.getMessage()));
        }
        else {
            System.out.println("Account was added to database");
            System.out.println("ID: " + response.getNewAccount().getUserID());
            System.out.println("Name: " + response.getNewAccount().getName());
        }
    }
}
