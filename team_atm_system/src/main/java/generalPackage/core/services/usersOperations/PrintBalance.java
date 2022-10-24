package generalPackage.core.services.usersOperations;

import generalPackage.core.database.Database;
import generalPackage.core.requests.usersRequests.PrintBalanceRequest;
import generalPackage.core.responses.usersResponses.CoreErrorUsers;
import generalPackage.core.responses.usersResponses.PrintBalanceResponse;
import generalPackage.core.services.usersOperations.usersValidators.PrintBalanceValidator;

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
