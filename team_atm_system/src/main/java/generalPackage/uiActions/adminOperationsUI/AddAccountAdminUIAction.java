package generalPackage.uiActions.adminOperationsUI;

import generalPackage.core.requests.adminRequests.AddAccountRequest;
import generalPackage.core.responses.adminResponses.AddAccountResponse;
import generalPackage.core.services.adminOperations.AddAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddAccountAdminUIAction implements AdminUIactions {

@Autowired
private AddAccountService addAccountService;


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
