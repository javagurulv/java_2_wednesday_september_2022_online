package generalPackage.adminOperationsUI;

import generalPackage.adminOperations.DeleteAccountService;
import generalPackage.adminRequests.DeleteAccountRequest;
import generalPackage.adminResponses.DeleteAccountResponse;

import java.util.Scanner;

public class DeleteAccountAdminUIAction implements AdminUIactions {

    private DeleteAccountService deleteAccountService;

    public DeleteAccountAdminUIAction(DeleteAccountService deleteAccountService) {
        this.deleteAccountService = deleteAccountService;
    }


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
