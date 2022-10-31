package generalPackage.usersOperationsUI;

import generalPackage.core.database.UserInfo;
import generalPackage.core.requests.usersRequests.DecreaseBalanceRequest;
import generalPackage.core.responses.usersResponses.DecreaseBalanceResponse;
import generalPackage.core.services.usersOperations.DecreaseBalance;

import java.util.Scanner;

public class DecreaseBalanceServiceUIAction implements UI_Menu {
    private DecreaseBalance decreaseBalance;
    private static UserInfo userInfo = new UserInfo();

    public DecreaseBalanceServiceUIAction(DecreaseBalance decreaseBalance) {
        this.decreaseBalance = decreaseBalance;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input amount to withdraw:");
        int amountToDecrease = scanner.nextInt();

        DecreaseBalanceRequest request = new DecreaseBalanceRequest(userInfo.getUserId(), amountToDecrease);
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
