package generalPackage.balanceOperationsUI;

import generalPackage.balanceOperations.IncreaseBalance;
import generalPackage.usersRequests.IncreaseBalanceRequest;
import generalPackage.usersResponses.IncreaseBalanceResponse;

import java.util.Scanner;

public class IncreaseBalanceServiceUIAction implements UI_Menu {

    private IncreaseBalance increaseBalance;

    public IncreaseBalanceServiceUIAction(IncreaseBalance increaseBalance) {
        this.increaseBalance = increaseBalance;
    }

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
