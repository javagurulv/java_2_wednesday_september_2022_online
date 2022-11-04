package generalPackage.core.services.adminOperations.adminValidators;

import generalPackage.core.requests.adminRequests.Ordering;
import generalPackage.core.responses.adminResponses.CoreError;
import generalPackage.dependencyInjection.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DIComponent
public class OrderingValidator {

    public List<CoreError> validate(Ordering ordering) {
        List<CoreError> errors = new ArrayList<>();
        validateOrderBy(ordering).ifPresent(errors::add);
        validateMandatoryOrderBy(ordering).ifPresent(errors::add);
        validateOrderDirection(ordering).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateOrderBy(Ordering ordering) {
        return (ordering.getOrderBy() != null
                && !(ordering.getOrderBy().equals("name") || ordering.getOrderBy().equals("balance")))
                ? Optional.of(new CoreError("Field order by ", "shoud contain 'name' or 'id' only"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryOrderBy(Ordering ordering) {
        return (ordering.getOrderBy() == null && ordering.getOrderDirection() != null)
                ? Optional.of(new CoreError("OrderBy ", "should not be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateOrderDirection(Ordering ordering) {
        return (ordering.getOrderDirection() != null
                && !(ordering.getOrderDirection().equals("ASC") || ordering.getOrderDirection().equals("DESC")))
                ? Optional.of(new CoreError("OrderDirection ", "should contain 'ASC' or 'DESC' only"))
                : Optional.empty();
    }
}
