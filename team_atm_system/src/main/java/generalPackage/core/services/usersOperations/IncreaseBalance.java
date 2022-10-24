package generalPackage.core.services.usersOperations;

import generalPackage.core.database.Database;
import generalPackage.core.requests.usersRequests.IncreaseBalanceRequest;
import generalPackage.core.responses.usersResponses.CoreErrorUsers;
import generalPackage.core.responses.usersResponses.IncreaseBalanceResponse;
import generalPackage.core.services.usersOperations.usersValidators.IncreaseBalanceValidator;

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

