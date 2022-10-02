package myApp.core.services.validators;

import myApp.core.requests.SearchBankAccountRequest;
import myApp.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchBankAccountValidator {

    public List<CoreError> validate(SearchBankAccountRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateName(request).ifPresent(errors::add);
        validateSurname(request).ifPresent(errors::add);
        validatePersonalCode(request).ifPresent(errors::add);
        if (request.getOrder() != null) {
            validateOrderBy(request).ifPresent(errors::add);
            validateOrderDirection(request).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validateName(SearchBankAccountRequest request) {
        return request.getName() != null ?
                Optional.empty() :
                Optional.of(new CoreError("Name", "Name may contains only letters " +
                        "and cannot be empty"));
    }

    private Optional<CoreError> validateSurname(SearchBankAccountRequest request) {
        return request.getSurname() != null
                ? Optional.empty()
                : Optional.of(new CoreError("Surname", "Surname may contains only letters " +
                "and cannot be empty"));
    }

    private Optional<CoreError> validatePersonalCode(SearchBankAccountRequest request) {
        return request.getName() != null
                ? Optional.empty()
                : Optional.of(new CoreError("Personal code", "Personal code may contains only numbers " +
                "and cannot be empty"));
    }

    private Optional<CoreError> validateOrderBy(SearchBankAccountRequest request) {
        return request.getOrder().getOrderBy().equals("name")
                || request.getOrder().getOrderBy().equals("surname")
                || request.getOrder().getOrderBy().equals("personal code")
                || request.getOrder().getOrderBy() != null
                ? Optional.empty()
                : Optional.of(new CoreError("Order by", "Order by cannot be empty and " +
                "can only contain a 'name' or 'surname' or 'personal code'"));
    }

    private Optional<CoreError> validateOrderDirection(SearchBankAccountRequest request) {
        return request.getOrder().getOrderDirection().equals("ASCENDING")
                || request.getOrder().getOrderDirection().equals("DESCENDING")
                || request.getOrder().getOrderDirection() != null
                ? Optional.empty()
                : Optional.of(new CoreError("Order direction", "Order direction cannot be empty and" +
                "can only contain a 'ASCENDING' or 'DESCENDING'"));
    }
}