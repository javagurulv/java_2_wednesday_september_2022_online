package lv.javaguru.java2.atmapp.adminServicesUI;

import lv.javaguru.java2.atmapp.adminServices.AddAccountService;

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
        addAccountService.execute(userName, userID);
        System.out.println("Account was successfully created");
    }
}
