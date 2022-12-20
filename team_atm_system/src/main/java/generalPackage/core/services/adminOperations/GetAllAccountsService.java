package generalPackage.core.services.adminOperations;


import generalPackage.core.database.AccountsRepository;
import generalPackage.core.domain.Accounts;
import generalPackage.core.requests.adminRequests.GetAllAccountsRequest;
import generalPackage.core.requests.adminRequests.Ordering;
import generalPackage.core.responses.adminResponses.CoreError;
import generalPackage.core.responses.adminResponses.GetAllAccountsResponse;
import generalPackage.core.services.adminOperations.adminValidators.GetAllAccountsServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class GetAllAccountsService {

@Value("${search.ordering.enabled}")
private boolean orderingEnabled;

    @Autowired
    private AccountsRepository accountsRepository;
    @Autowired
    private GetAllAccountsServiceValidator validator;


    public GetAllAccountsResponse execute(GetAllAccountsRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetAllAccountsResponse(null, errors);
        }

        List<Accounts> accounts = accountsRepository.getAllAccounts();
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