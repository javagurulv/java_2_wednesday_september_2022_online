package generalPackage.usersOperationsUI;

import generalPackage.core.database.UserInfo;
import generalPackage.core.requests.usersRequests.IncreaseBalanceRequest;
import generalPackage.core.responses.usersResponses.IncreaseBalanceResponse;
import generalPackage.core.services.usersOperations.IncreaseBalance;

import java.util.Scanner;

public class IncreaseBalanceServiceUIAction implements UI_Menu {

    private IncreaseBalance increaseBalance;
    private static UserInfo userInfo = new UserInfo();

    public IncreaseBalanceServiceUIAction(IncreaseBalance increaseBalance) {
        this.increaseBalance = increaseBalance;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, input amount for deposit : ");
        int amountToDeposit = scanner.nextInt();

        IncreaseBalanceRequest request = new IncreaseBalanceRequest(userInfo.getUserId(), amountToDeposit);
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
