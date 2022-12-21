package generalPackage.core.services.adminOperations;


import generalPackage.core.database.AccountsRepository;
import generalPackage.core.domain.Accounts;
import generalPackage.core.requests.adminRequests.AddAccountRequest;
import generalPackage.core.responses.adminResponses.AddAccountResponse;
import generalPackage.core.responses.adminResponses.CoreError;
import generalPackage.core.services.adminOperations.adminValidators.AddAccountServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
@Transactional
public class AddAccountService {

    @Autowired
    private AccountsRepository accountsRepository;
    @Autowired
    private AddAccountServiceValidator validator;


    @Transactional
    public AddAccountResponse execute(AddAccountRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddAccountResponse(errors);
        }
        Accounts account = new Accounts(request.getUserName(), request.getUserId(), 0);
        accountsRepository.addAccount(account);
        return new AddAccountResponse(account);
    }
}
