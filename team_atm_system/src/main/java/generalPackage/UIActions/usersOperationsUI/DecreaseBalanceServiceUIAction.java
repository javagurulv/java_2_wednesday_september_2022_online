package generalPackage.UIActions.usersOperationsUI;

import generalPackage.core.requests.usersRequests.DecreaseBalanceRequest;
import generalPackage.core.responses.usersResponses.DecreaseBalanceResponse;
import generalPackage.core.services.usersOperations.DecreaseBalance;
import generalPackage.dependencyInjection.DIComponent;
import generalPackage.dependencyInjection.DIDependency;

import java.util.Scanner;

@DIComponent
public class DecreaseBalanceServiceUIAction implements UI_Menu {

@DIDependency
private DecreaseBalance decreaseBalance;


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please confirm your ID: ");
        int userID = scanner.nextInt();
        System.out.println("Please input amount to withdraw:");
        int amountToDecrease = scanner.nextInt();
        DecreaseBalanceRequest request = new DecreaseBalanceRequest(userID, amountToDecrease);
        DecreaseBalanceResponse response = decreaseBalance.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreErrorUsers ->
                    System.out.println("Operation failed." + coreErrorUsers.getField() + " " + coreErrorUsers.getMessage()));
        } else if (response.isMoneyWithdraw()) {
            String message = ("Amount %d was successfully debited from your account");
            System.out.println(String.format(message, amountToDecrease));
        }
    }
}