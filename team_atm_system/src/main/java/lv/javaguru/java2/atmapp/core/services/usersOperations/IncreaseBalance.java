package lv.javaguru.java2.atmapp.core.services.usersOperations;

import lv.javaguru.java2.atmapp.core.database.Database;
import lv.javaguru.java2.atmapp.core.requests.usersRequests.IncreaseBalanceRequest;
import lv.javaguru.java2.atmapp.core.responses.usersResponses.CoreErrorUsers;
import lv.javaguru.java2.atmapp.core.responses.usersResponses.IncreaseBalanceResponse;
import lv.javaguru.java2.atmapp.core.services.usersOperations.usersValidators.IncreaseBalanceValidator;

import java.util.List;

public class IncreaseBalance {

    private Database database;
    private IncreaseBalanceValidator validator;

    public IncreaseBalance(Database database, IncreaseBalanceValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public IncreaseBalanceResponse execute(IncreaseBalanceRequest request) {
        List<CoreErrorUsers> errorUsers = validator.validate(request);
        if (!errorUsers.isEmpty()) {
            return new IncreaseBalanceResponse(errorUsers);
        }
        boolean balanceChanged = database.increaseBalance(request.getUserID(), request.getAmountToIncrease());
        return new IncreaseBalanceResponse(balanceChanged);
    }
}

