package generalPackage.UIActions.adminOperationsUI;

import generalPackage.core.requests.adminRequests.DeleteAccountRequest;
import generalPackage.core.responses.adminResponses.DeleteAccountResponse;
import generalPackage.core.services.adminOperations.DeleteAccountService;
import generalPackage.dependencyInjection.DIComponent;
import generalPackage.dependencyInjection.DIDependency;

import java.util.Scanner;

@DIComponent
public class DeleteAccountAdminUIAction implements AdminUIactions {

    @DIDependency
    private DeleteAccountService deleteAccountService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user ID to delete account: ");
        int userID = scanner.nextInt();
        DeleteAccountRequest request = new DeleteAccountRequest(userID);
        DeleteAccountResponse response = deleteAccountService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Account was NOT deleted " + coreError.getField() + " " + coreError.getMessage()));
        } else if (response.isAccountDeleted()) {
            System.out.println("Account with ID Nr." + userID + " was successfully deleted.");
        } else {
            System.out.println("Account was NOT deleted ");
        }
    }
}
