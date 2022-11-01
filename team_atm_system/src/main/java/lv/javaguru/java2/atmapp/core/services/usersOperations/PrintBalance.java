package lv.javaguru.java2.atmapp.core.services.usersOperations;

import lv.javaguru.java2.atmapp.core.database.Database;
import lv.javaguru.java2.atmapp.core.requests.usersRequests.PrintBalanceRequest;
import lv.javaguru.java2.atmapp.core.responses.usersResponses.CoreErrorUsers;
import lv.javaguru.java2.atmapp.core.responses.usersResponses.PrintBalanceResponse;
import lv.javaguru.java2.atmapp.core.services.usersOperations.usersValidators.PrintBalanceValidator;

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
