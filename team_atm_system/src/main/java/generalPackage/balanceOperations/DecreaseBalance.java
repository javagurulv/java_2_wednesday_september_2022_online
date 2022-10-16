package generalPackage.balanceOperations;

import generalPackage.database.Database;
import generalPackage.usersRequests.DecreaseBalanceRequest;
import generalPackage.usersResponses.CoreErrorUsers;
import generalPackage.usersResponses.DecreaseBalanceResponse;

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
