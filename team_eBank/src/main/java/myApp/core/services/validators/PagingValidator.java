package myApp.core.services.validators;

import myApp.core.requests.Paging;
import myApp.core.responses.CoreError;
import myApp.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DIComponent
public class PagingValidator {

    public List<CoreError> validate(Paging paging) {
        List<CoreError> errors = new ArrayList<>();
        validatePageNumber(paging).ifPresent(errors::add);
        validatePageSize(paging).ifPresent(errors::add);
        validateObligatoryFieldsPageNumber(paging).ifPresent(errors::add);
        validateObligatoryFieldsPageSize(paging).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validatePageNumber(Paging paging) {
        return (paging.getPageNumber() != null
                && paging.getPageNumber() <= 0)
                ? Optional.of(new CoreError("Page number", "Must be greater then 0"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePageSize(Paging paging) {
        return (paging.getPageSize() != null
                && paging.getPageSize() <= 0)
                ? Optional.of(new CoreError("Page size", "Must be greater then 0"))
                : Optional.empty();
    }

    private Optional<CoreError> validateObligatoryFieldsPageNumber(Paging paging) {
        return (paging.getPageNumber() == null && paging.getPageSize() != null)
                ? Optional.of(new CoreError("Page number", "Must not be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateObligatoryFieldsPageSize(Paging paging) {
        return (paging.getPageSize() == null && paging.getPageNumber() != null)
                ? Optional.of(new CoreError("Page size", "Must not be empty"))
                : Optional.empty();
    }

}
