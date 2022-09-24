package atmApplication.Menu.InterfaceActions;

import atmApplication.BalanceOperations.DecreaseBalance;
import atmApplication.Database.AccountDatabaseImpl;
import atmApplication.Database.Database;

import java.util.Scanner;


public class Withdraw implements UI_Menu {

    private Database database = new AccountDatabaseImpl();

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, input amount for withdraw : ");
        int amountToWithdraw = scanner.nextInt();
        DecreaseBalance withdraw = new DecreaseBalance(amountToWithdraw, 1234);
        withdraw.execute(database);
    }
}
