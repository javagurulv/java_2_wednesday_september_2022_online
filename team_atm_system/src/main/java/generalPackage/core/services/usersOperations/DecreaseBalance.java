package generalPackage.core.services.usersOperations;

import generalPackage.core.database.Database;
import generalPackage.core.requests.usersRequests.DecreaseBalanceRequest;
import generalPackage.core.responses.usersResponses.CoreErrorUsers;
import generalPackage.core.responses.usersResponses.DecreaseBalanceResponse;
import generalPackage.core.services.usersOperations.usersValidators.DecreaseBalanceValidator;

import java.util.List;

public class DecreaseBalance  {

    private Database database;
    DecreaseBalanceValidator validator;

    public DecreaseBalance(Database database, DecreaseBalanceValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public DecreaseBalanceResponse execute(DecreaseBalanceRequest request) {
        List<CoreErrorUsers> errorUsers = validator.validate(request);
        if (!errorUsers.isEmpty()){
            return new DecreaseBalanceResponse(errorUsers);
        }
        boolean balanceChanged= database.decreaseBalance(request.getUserID(), request.getAmountToDecrease());
        return new DecreaseBalanceResponse(balanceChanged);
    }
}
