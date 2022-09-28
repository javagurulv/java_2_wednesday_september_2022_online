package generalPackage.adminOperationsUI;

import generalPackage.adminOperations.FindUserByIDServise;
import generalPackage.adminRequests.FindUserByIDRequest;
import generalPackage.adminResponses.FindByIDAccountResponse;

import java.util.Scanner;

public class FindUserAdminUIAction implements AdminUIactions {

    private FindUserByIDServise findUserByIDServise;

    public FindUserAdminUIAction(FindUserByIDServise findUserByIDServise) {
        this.findUserByIDServise = findUserByIDServise;
    }


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter user ID");
        int userID = scanner.nextInt();
        FindUserByIDRequest request = new FindUserByIDRequest(userID);
        FindByIDAccountResponse response = findUserByIDServise.execute(request);
     //   System.out.println(response.isAccountFound().getUserID());
        System.out.println(response.isAccountFound());
    }
}
