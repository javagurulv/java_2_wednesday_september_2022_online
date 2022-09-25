package lv.javaguru.java2.atmapp.AdminOperationsUI;

import lv.javaguru.java2.atmapp.AdminOperations.DeleteAccountService;

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
        deleteAccountService.execute(userID);
        System.out.println("Account was successfully deleted.");
    }
}
