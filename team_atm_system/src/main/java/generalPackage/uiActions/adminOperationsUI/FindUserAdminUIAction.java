package generalPackage.uiActions.adminOperationsUI;

import generalPackage.core.requests.adminRequests.FindUserByIDRequest;
import generalPackage.core.responses.adminResponses.FindByIDAccountResponse;
import generalPackage.core.services.adminOperations.FindUserByIDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class FindUserAdminUIAction implements AdminUIactions {

@Autowired
private FindUserByIDService findUserByIDService;


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter user ID");
        int userID = scanner.nextInt();
        FindUserByIDRequest request = new FindUserByIDRequest(userID);
        FindByIDAccountResponse response = findUserByIDService.execute(request);
        if (response.hasErrors()){
            response.getErrors().forEach(coreError ->
                    System.out.println("Search error: " + coreError.getField() + " " + coreError.getMessage()));
        }
        else {
            System.out.println(response.getAccountByID());
        }
    }
}
