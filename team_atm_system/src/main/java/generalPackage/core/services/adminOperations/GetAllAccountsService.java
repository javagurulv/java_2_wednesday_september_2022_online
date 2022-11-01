package generalPackage.core.services.adminOperations;


import generalPackage.Accounts;
import generalPackage.core.database.Database;
import generalPackage.core.requests.adminRequests.GetAllAccountsRequest;
import generalPackage.core.requests.adminRequests.Ordering;
import generalPackage.core.responses.adminResponses.CoreError;
import generalPackage.core.responses.adminResponses.GetAllAccountsResponse;
import generalPackage.core.services.adminOperations.adminValidators.GetAllAccountsServiceValidator;
import generalPackage.dependencyInjection.DIComponent;
import generalPackage.dependencyInjection.DIDependency;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@DIComponent
public class GetAllAccountsService {

    @DIDependency
    private Database database;
    @DIDependency
    private GetAllAccountsServiceValidator validator;

//    public GetAllAccountsService(Database database, GetAllAccountsServiceValidator validator) {
//        this.database = database;
//        this.validator = validator;
//    }

//    public GetAllAccountsResponse execute (GetAllAccountsRequest request){
//        List <Accounts> accounts = database.getAllAccounts();
//        return new GetAllAccountsResponse(accounts);
//    }

    public GetAllAccountsResponse execute(GetAllAccountsRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetAllAccountsResponse(null, errors);
        }

        List<Accounts> accounts = database.getAllAccounts();
        accounts = order(accounts, request.getOrdering());

        return new GetAllAccountsResponse(accounts, null);
    }


    private List<Accounts> order(List<Accounts> accounts, Ordering ordering) {
        if (ordering != null) {
            Comparator<Accounts> comparator = ordering.getOrderBy().equals("name")
                    ? Comparator.comparing(Accounts::getName)
                    : Comparator.comparing(Accounts::getBalance);
            if (ordering.getOrderDirection().equals("DESC")) {
                comparator = comparator.reversed();
            }
            return accounts.stream().sorted(comparator).collect(Collectors.toList());
        } else {

            return accounts;
        }
    }
}