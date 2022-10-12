package generalPackage.balanceOperationsUI;

import generalPackage.balanceOperations.PrintBalance;
import generalPackage.usersRequests.PrintBalanceRequest;
import generalPackage.usersResponses.PrintBalanceResponse;

import java.util.Scanner;


public class PrintBalanceServiceUIAction implements UI_Menu {

    private PrintBalance printBalance;

    public PrintBalanceServiceUIAction(PrintBalance printBalance) {
        this.printBalance = printBalance;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please confirm your ID: ");
        int userID = scanner.nextInt();
        PrintBalanceRequest request = new PrintBalanceRequest(userID);
        PrintBalanceResponse response = printBalance.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreErrorUsers ->
                    System.out.println("Operation failed." + coreErrorUsers.getField() + "" + coreErrorUsers.getMessage()));
        } else {
            System.out.println("Your current balance is " + response.getBalanceByID());

        }
    }
}
