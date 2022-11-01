package generalPackage.core.services.usersOperations;

import generalPackage.core.database.Database;
import generalPackage.core.requests.usersRequests.DecreaseBalanceRequest;
import generalPackage.core.responses.usersResponses.CoreErrorUsers;
import generalPackage.core.responses.usersResponses.DecreaseBalanceResponse;
import generalPackage.core.services.usersOperations.usersValidators.DecreaseBalanceValidator;
import generalPackage.dependencyInjection.DIComponent;
import generalPackage.dependencyInjection.DIDependency;

import java.util.List;

@DIComponent
public class DecreaseBalance {

    @DIDependency
    private Database database;
    @DIDependency
    DecreaseBalanceValidator validator;


//    public DecreaseBalance(Database database, DecreaseBalanceValidator validator) {
//        this.database = database;
//        this.validator = validator;
//    }

    public DecreaseBalanceResponse execute(DecreaseBalanceRequest request) {
        List<CoreErrorUsers> errorUsers = validator.validate(request);
        if (!errorUsers.isEmpty()) {
            return new DecreaseBalanceResponse(errorUsers);
        }
        boolean balanceChanged = database.decreaseBalance(request.getUserID(), request.getAmountToDecrease());
        return new DecreaseBalanceResponse(balanceChanged);
    }
}
