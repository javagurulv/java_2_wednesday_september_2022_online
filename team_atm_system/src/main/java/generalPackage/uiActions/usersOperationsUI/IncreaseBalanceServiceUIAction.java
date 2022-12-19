package generalPackage.uiActions.usersOperationsUI;

import generalPackage.core.requests.usersRequests.IncreaseBalanceRequest;
import generalPackage.core.responses.usersResponses.IncreaseBalanceResponse;
import generalPackage.core.services.usersOperations.IncreaseBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class IncreaseBalanceServiceUIAction implements UI_Menu {

    @Autowired
    private IncreaseBalance increaseBalance;


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please confirm your ID: ");
        int userID = scanner.nextInt();
        System.out.print("Please, input amount for deposit : ");
        int amountToDeposit = scanner.nextInt();
        IncreaseBalanceRequest request = new IncreaseBalanceRequest(userID, amountToDeposit);
        IncreaseBalanceResponse response = increaseBalance.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreErrorUsers ->
                    System.out.println("Operation failed." + coreErrorUsers.getField() + " " + coreErrorUsers.getMessage()));
        } else if (response.isMoneyAdded()) {
            String message = ("Amount %d was successfully added to your account");
            System.out.println(String.format(message, amountToDeposit));
        }
    }
}
