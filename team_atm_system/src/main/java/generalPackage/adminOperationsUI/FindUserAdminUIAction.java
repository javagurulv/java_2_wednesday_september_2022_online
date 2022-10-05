package generalPackage.adminOperationsUI;

import generalPackage.adminOperations.FindUserByIDService;
import generalPackage.adminRequests.FindUserByIDRequest;
import generalPackage.adminResponses.FindByIDAccountResponse;

import java.util.Scanner;

public class FindUserAdminUIAction implements AdminUIactions {

    private FindUserByIDService findUserByIDService;

    public FindUserAdminUIAction(FindUserByIDService findUserByIDService) {
        this.findUserByIDService = findUserByIDService;
    }


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
