package lv.javaguru.java2.atmapp.adminServicesUI;

import lv.javaguru.java2.atmapp.adminServices.DeleteAccountService;
import lv.javaguru.java2.atmapp.requests.adminRequests.DeleteAccountRequest;
import lv.javaguru.java2.atmapp.responses.adminResponses.DeleteAccountResponse;

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

        if (response.isAccountToDelete()) {
            System.out.println("Account was successfully deleted.");
        } else {
            System.out.println("Account wasn't deleted. Try again.");
        }
    }
}
