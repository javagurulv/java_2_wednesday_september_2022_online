package generalPackage.core.services.adminOperations;

import generalPackage.core.database.AccountsRepository;
import generalPackage.core.domain.Accounts;
import generalPackage.core.requests.adminRequests.FindUserByIDRequest;
import generalPackage.core.responses.adminResponses.CoreError;
import generalPackage.core.responses.adminResponses.FindByIDAccountResponse;
import generalPackage.core.services.adminOperations.adminValidators.FindUserByIDServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindUserByIDService {

    @Autowired
    private AccountsRepository accountsRepository;
    @Autowired
    private FindUserByIDServiceValidator validator;


    public FindByIDAccountResponse execute(FindUserByIDRequest request) {
        List <CoreError> errors =validator.validate(request);
        if (!errors.isEmpty()){
            return new FindByIDAccountResponse(errors);
        }
        Accounts accountToFind = accountsRepository.findUserByID(request.getUserIDtoFind());
        return new FindByIDAccountResponse(accountToFind);

    }
}
