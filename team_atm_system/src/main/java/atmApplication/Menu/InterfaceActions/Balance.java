package atmApplication.Menu.InterfaceActions;

import atmApplication.BalanceOperations.PrintBalance;
import atmApplication.Database.AccountDatabaseImpl;
import atmApplication.Database.Database;

public class Balance implements UI_Menu {

    private Database database = new AccountDatabaseImpl();

    @Override
    public void execute() {
        PrintBalance printBalance = new PrintBalance(1234);
        printBalance.execute(database);
    }
}
