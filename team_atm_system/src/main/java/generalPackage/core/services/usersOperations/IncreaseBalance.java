package generalPackage.core.services.usersOperations;

import generalPackage.core.database.Database;
import generalPackage.core.requests.usersRequests.IncreaseBalanceRequest;
import generalPackage.core.responses.usersResponses.CoreErrorUsers;
import generalPackage.core.responses.usersResponses.IncreaseBalanceResponse;
import generalPackage.core.services.usersOperations.usersValidators.IncreaseBalanceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IncreaseBalance {

    @Autowired
    private Database database;
    @Autowired
    private IncreaseBalanceValidator validator;


    public IncreaseBalanceResponse execute(IncreaseBalanceRequest request) {
        List<CoreErrorUsers> errorUsers = validator.validate(request);
        if (!errorUsers.isEmpty()) {
            return new IncreaseBalanceResponse(errorUsers);
        }
        boolean balanceChanged = database.increaseBalance(request.getUserID(), request.getAmountToIncrease());
        return new IncreaseBalanceResponse(balanceChanged);
    }
}

