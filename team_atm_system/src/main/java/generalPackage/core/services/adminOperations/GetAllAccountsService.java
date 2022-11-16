package generalPackage.core.services.adminOperations;


import generalPackage.Accounts;
import generalPackage.core.database.Database;
import generalPackage.core.requests.adminRequests.GetAllAccountsRequest;
import generalPackage.core.requests.adminRequests.Ordering;
import generalPackage.core.responses.adminResponses.CoreError;
import generalPackage.core.responses.adminResponses.GetAllAccountsResponse;
import generalPackage.core.services.adminOperations.adminValidators.GetAllAccountsServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetAllAccountsService {

    @Autowired
    private Database database;
    @Autowired
    private GetAllAccountsServiceValidator validator;


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