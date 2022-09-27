package lv.javaguru.java2.atmapp.adminServicesUI;

import lv.javaguru.java2.atmapp.adminServices.AddAccountService;
import lv.javaguru.java2.atmapp.requests.adminRequests.AddAccountRequest;
import lv.javaguru.java2.atmapp.responses.adminResponses.AddAccountResponse;

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
        System.out.println("Account was successfully created");
    }
}
