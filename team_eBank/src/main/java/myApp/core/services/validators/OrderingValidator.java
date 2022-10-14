package myApp.core.services.validators;

import myApp.core.requests.SearchBankAccountRequest;
import myApp.core.responses.CoreError;
import myApp.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DIComponent
public class OrderingValidator {

    public List<CoreError> validateOrdering(SearchBankAccountRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateOrderBy(request).ifPresent(errors::add);
        validateOrderDirection(request).ifPresent(errors::add);
        validateObligatoryFieldsOrderBy(request).ifPresent(errors::add);
        validateObligatoryFieldsOrderDirection(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateOrderBy(SearchBankAccountRequest request) {
        return (request.getOrder().getOrderBy() != null && !(request.getOrder().getOrderBy().equals("name")
                || request.getOrder().getOrderBy().equals("surname")
                || request.getOrder().getOrderBy().equals("personal code")))
                ? Optional.of(new CoreError("Order by", "Order by cannot be empty and " +
                "can only contain a 'name' or 'surname' or 'personal code'"))
                : Optional.empty();
    }

    private Optional<CoreError> validateOrderDirection(SearchBankAccountRequest request) {
        return (request.getOrder().getOrderDirection() != null &&
                !(request.getOrder().getOrderDirection().equals("ASCENDING") ||
                        request.getOrder().getOrderDirection().equals("DESCENDING")))
                ? Optional.of(new CoreError("Order direction", "Order direction cannot be empty and" +
                " can only contain a 'ASCENDING' or 'DESCENDING'"))
                : Optional.empty();
    }

    private Optional<CoreError> validateObligatoryFieldsOrderBy(SearchBankAccountRequest request) {
        return (request.getOrder().getOrderDirection() != null && request.getOrder().getOrderBy() == null)
                ? Optional.of(new CoreError("Order by", "Must not be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateObligatoryFieldsOrderDirection(SearchBankAccountRequest request) {
        return (request.getOrder().getOrderBy() != null && request.getOrder().getOrderDirection() == null)
                ? Optional.of(new CoreError("Order direction", "Must not be empty"))
                : Optional.empty();
    }

}
