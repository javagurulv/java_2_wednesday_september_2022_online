package generalPackage.balanceOperations;

import generalPackage.database.Database;
import generalPackage.usersRequests.IncreaseBalanceRequest;
import generalPackage.usersResponses.CoreErrorUsers;
import generalPackage.usersResponses.IncreaseBalanceResponse;

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

