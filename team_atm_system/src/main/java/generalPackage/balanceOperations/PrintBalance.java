package generalPackage.balanceOperations;

import generalPackage.database.Database;
import generalPackage.usersRequests.PrintBalanceRequest;
import generalPackage.usersResponses.CoreErrorUsers;
import generalPackage.usersResponses.PrintBalanceResponse;

import java.util.List;

public class PrintBalance {

    private Database database;
    private PrintBalanceValidator validator;

    public PrintBalance(Database database, PrintBalanceValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public PrintBalanceResponse execute(PrintBalanceRequest request) {
        List<CoreErrorUsers> errorUsers = validator.validate(request);
        if (!errorUsers.isEmpty()) {
            return new PrintBalanceResponse(errorUsers);
        } else {
            return new PrintBalanceResponse(database.printBalance(request.getUserIDtoGetBalance()));
        }
    }
}
