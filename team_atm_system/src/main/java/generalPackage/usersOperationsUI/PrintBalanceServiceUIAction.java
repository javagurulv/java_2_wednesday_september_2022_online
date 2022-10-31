package generalPackage.usersOperationsUI;

import generalPackage.core.database.UserInfo;
import generalPackage.core.requests.usersRequests.PrintBalanceRequest;
import generalPackage.core.responses.usersResponses.PrintBalanceResponse;
import generalPackage.core.services.usersOperations.PrintBalance;

import java.util.Scanner;


public class PrintBalanceServiceUIAction implements UI_Menu {

    private PrintBalance printBalance;
    private static UserInfo userInfo = new UserInfo();

    public PrintBalanceServiceUIAction(PrintBalance printBalance) {
        this.printBalance = printBalance;
    }

    @Override
    public void execute() {

        PrintBalanceRequest request = new PrintBalanceRequest(userInfo.getUserId());
        PrintBalanceResponse response = printBalance.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreErrorUsers ->
                    System.out.println("Operation failed." + coreErrorUsers.getField() + "" + coreErrorUsers.getMessage()));
        } else {
            System.out.println("Your current balance is " + response.getBalanceByID());

        }
    }
}
