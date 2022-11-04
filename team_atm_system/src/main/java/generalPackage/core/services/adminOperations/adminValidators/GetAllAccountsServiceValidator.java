package generalPackage.core.services.adminOperations.adminValidators;

// import ordering.adminRequests.GetAllAccountsRequest;
// import ordering.adminRequests.Ordering;
// import ordering.adminRequests.OrderingValidator;
// import ordering.adminResponses.CoreError;

import generalPackage.core.requests.adminRequests.GetAllAccountsRequest;
import generalPackage.core.requests.adminRequests.Ordering;
import generalPackage.core.responses.adminResponses.CoreError;
import generalPackage.dependencyInjection.DIComponent;
import generalPackage.dependencyInjection.DIDependency;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class GetAllAccountsServiceValidator {

    @DIDependency
    private OrderingValidator orderingValidator;


    public List<CoreError> validate(GetAllAccountsRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateOrderingIfPresent(request, errors);
        return errors;
    }

    private void validateOrderingIfPresent(GetAllAccountsRequest request, List<CoreError> errors) {
        if (request.getOrdering() != null) {
            Ordering ordering = request.getOrdering();
            errors.addAll(orderingValidator.validate(ordering));
        }
    }
}
