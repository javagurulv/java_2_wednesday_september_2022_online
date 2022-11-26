package generalPackage.core.services.adminOperations;

import generalPackage.core.database.AccountsRepository;
import generalPackage.core.requests.adminRequests.DeleteAccountRequest;
import generalPackage.core.responses.adminResponses.CoreError;
import generalPackage.core.responses.adminResponses.DeleteAccountResponse;
import generalPackage.core.services.adminOperations.adminValidators.DeleteAccountServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;



@Component
public class DeleteAccountService {

    @Autowired
    private AccountsRepository accountsRepository;
    @Autowired
    private DeleteAccountServiceValidator validator;


    public DeleteAccountResponse execute(DeleteAccountRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()){
            return new DeleteAccountResponse(errors);
        }
        boolean accountDeleted = accountsRepository.deleteAccount(request.getUserIDtoDelete());
        return new DeleteAccountResponse(accountDeleted);
    }
}
