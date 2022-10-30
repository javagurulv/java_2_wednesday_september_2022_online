package lv.javaguru.java2.repo_men_inc.core.validators;

import lv.javaguru.java2.repo_men_inc.core.requests.Paging;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PagingValidator {

    public List<CoreError> validate(Paging paging) {
        List<CoreError> errors = new ArrayList<>();

        if (paging.getPageSize() != null && paging.getPageNumber() != null) {
            validatePagingPageNumber(paging).ifPresent(errors::add);
            validatePagingPageSize(paging).ifPresent(errors::add);
        }
        validatePagingPageSizeIsPresent(paging).ifPresent(errors::add);
        validatePagingPageNumberIsPresent(paging).ifPresent(errors::add);

        return errors;
    }

    private Optional<CoreError> validatePagingPageNumber(Paging paging) {
        return paging.getPageNumber() <= 0
                ? Optional.of(new CoreError("PageNumber", "Must be greater than zero!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePagingPageSize(Paging paging) {
        return paging.getPageSize() <= 0
                ? Optional.of(new CoreError("PageSize", "Must be greater than zero!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePagingPageSizeIsPresent(Paging paging) {
        return !isNull(paging.getPageSize()) && isNull(paging.getPageNumber())
                ? Optional.of(new CoreError("PageSize", "Cannot be empty if PageNumber is provided!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePagingPageNumberIsPresent(Paging paging) {
        return isNull(paging.getPageSize()) && !isNull(paging.getPageNumber())
                ? Optional.of(new CoreError("PageNumber", "Cannot be empty if PageSize is provided!"))
                : Optional.empty();
    }

    private boolean isNull(Integer integer) {
        return integer == null;
    }
}
