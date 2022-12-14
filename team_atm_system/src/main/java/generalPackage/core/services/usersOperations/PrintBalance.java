package generalPackage.core.services.usersOperations;

import generalPackage.core.database.AccountsRepository;
import generalPackage.core.requests.usersRequests.PrintBalanceRequest;
import generalPackage.core.responses.usersResponses.CoreErrorUsers;
import generalPackage.core.responses.usersResponses.PrintBalanceResponse;
import generalPackage.core.services.usersOperations.usersValidators.PrintBalanceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrintBalance {

    @Autowired
    private AccountsRepository accountsRepository;
    @Autowired
    private PrintBalanceValidator validator;


    public PrintBalanceResponse execute(PrintBalanceRequest request) {
        List<CoreErrorUsers> errorUsers = validator.validate(request);
        if (!errorUsers.isEmpty()) {
            return new PrintBalanceResponse(errorUsers);
        } else {
            return new PrintBalanceResponse(accountsRepository.printBalance(request.getUserIDtoGetBalance()));
        }
    }
}
