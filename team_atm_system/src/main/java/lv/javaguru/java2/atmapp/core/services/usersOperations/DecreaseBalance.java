package lv.javaguru.java2.atmapp.core.services.usersOperations;

import lv.javaguru.java2.atmapp.core.database.Database;
import lv.javaguru.java2.atmapp.core.requests.usersRequests.DecreaseBalanceRequest;
import lv.javaguru.java2.atmapp.core.responses.usersResponses.CoreErrorUsers;
import lv.javaguru.java2.atmapp.core.responses.usersResponses.DecreaseBalanceResponse;
import lv.javaguru.java2.atmapp.core.services.usersOperations.usersValidators.DecreaseBalanceValidator;

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
