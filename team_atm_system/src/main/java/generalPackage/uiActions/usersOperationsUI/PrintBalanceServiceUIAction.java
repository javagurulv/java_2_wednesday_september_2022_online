package generalPackage.uiActions.usersOperationsUI;

import generalPackage.core.requests.usersRequests.PrintBalanceRequest;
import generalPackage.core.responses.usersResponses.PrintBalanceResponse;
import generalPackage.core.services.usersOperations.PrintBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;


@Component
public class PrintBalanceServiceUIAction implements UI_Menu {

@Autowired
private PrintBalance printBalance;


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
