package generalPackage.core.services.adminOperations;

import generalPackage.core.database.AccountsRepository;
import generalPackage.core.domain.Accounts;
import generalPackage.core.requests.adminRequests.SearchAccountsServiceRequest;
import generalPackage.core.responses.adminResponses.CoreError;
import generalPackage.core.responses.adminResponses.SearchAccountsServiceResponse;
import generalPackage.core.services.adminOperations.adminValidators.SearchAccountsServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchAccountsService {

    @Autowired
    private AccountsRepository accountsRepository;
    @Autowired
    private SearchAccountsServiceValidator validator;


    public SearchAccountsServiceResponse execute(SearchAccountsServiceRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchAccountsServiceResponse(null, errors);
        }
        List<Accounts> accounts;
        accounts = accountsRepository.searchAccountByName(request.getUserNameToFind());
        return new SearchAccountsServiceResponse(accounts, null);
    }
}
