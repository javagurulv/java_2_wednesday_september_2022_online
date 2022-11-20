package lv.javaguru.java2.atmapp.usersOperationsUI;

import lv.javaguru.java2.atmapp.core.database.UserInfo;
import lv.javaguru.java2.atmapp.core.requests.usersRequests.PrintBalanceRequest;
import lv.javaguru.java2.atmapp.core.responses.usersResponses.PrintBalanceResponse;
import lv.javaguru.java2.atmapp.core.services.usersOperations.PrintBalance;


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
