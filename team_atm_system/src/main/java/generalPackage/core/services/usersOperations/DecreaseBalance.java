package generalPackage.core.services.usersOperations;

import generalPackage.core.database.AccountsRepository;
import generalPackage.core.requests.usersRequests.DecreaseBalanceRequest;
import generalPackage.core.responses.usersResponses.CoreErrorUsers;
import generalPackage.core.responses.usersResponses.DecreaseBalanceResponse;
import generalPackage.core.services.usersOperations.usersValidators.DecreaseBalanceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DecreaseBalance {

    @Autowired
    private AccountsRepository accountsRepository;
    @Autowired
    DecreaseBalanceValidator validator;


    public DecreaseBalanceResponse execute(DecreaseBalanceRequest request) {
        List<CoreErrorUsers> errorUsers = validator.validate(request);
        if (!errorUsers.isEmpty()) {
            return new DecreaseBalanceResponse(errorUsers);
        }
        boolean balanceChanged = accountsRepository.decreaseBalance(request.getUserID(), request.getAmountToDecrease());
        return new DecreaseBalanceResponse(balanceChanged);
    }
}
