package generalPackage.core.services.adminOperations;

import generalPackage.Accounts;
import generalPackage.core.database.Database;
import generalPackage.core.requests.adminRequests.SearchAccountsServiceRequest;
import generalPackage.core.responses.adminResponses.CoreError;
import generalPackage.core.responses.adminResponses.SearchAccountsServiceResponse;
import generalPackage.core.services.adminOperations.adminValidators.SearchAccountsServiceValidator;
import generalPackage.dependencyInjection.DIComponent;
import generalPackage.dependencyInjection.DIDependency;

import java.util.List;

@DIComponent
public class SearchAccountsService {

    @DIDependency
    private Database database;
    @DIDependency
    private SearchAccountsServiceValidator validator;


//    public SearchAccountsService(Database database, SearchAccountsServiceValidator validaror) {
//        this.database = database;
//        this.validator = validaror;
//    }

    public SearchAccountsServiceResponse execute(SearchAccountsServiceRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchAccountsServiceResponse(null, errors);
        }
        List<Accounts> accounts;
        accounts = database.searchAccountByName(request.getUserNameToFind());
        return new SearchAccountsServiceResponse(accounts, null);
    }
}
