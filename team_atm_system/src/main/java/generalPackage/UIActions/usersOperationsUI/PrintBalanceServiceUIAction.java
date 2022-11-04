package generalPackage.UIActions.usersOperationsUI;

import generalPackage.core.requests.usersRequests.PrintBalanceRequest;
import generalPackage.core.responses.usersResponses.PrintBalanceResponse;
import generalPackage.core.services.usersOperations.PrintBalance;
import generalPackage.dependencyInjection.DIComponent;
import generalPackage.dependencyInjection.DIDependency;

import java.util.Scanner;


@DIComponent
public class PrintBalanceServiceUIAction implements UI_Menu {

@DIDependency
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
