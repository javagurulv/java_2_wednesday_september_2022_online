package lv.javaguru.java2.repo_men_inc.core.validators;

import lv.javaguru.java2.repo_men_inc.core.requests.OrderingDirection;
import lv.javaguru.java2.repo_men_inc.core.requests.OrderingType;
import lv.javaguru.java2.repo_men_inc.core.requests.SearchDebtorRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchDebtorValidator {

    public List<CoreError> validate(SearchDebtorRequest searchDebtorRequest) {
        List<CoreError> errors = new ArrayList<>();
        validateSearchParameters(searchDebtorRequest).ifPresent(errors::add);

        if (searchDebtorRequest.getOrdering() != null) {
            validateOrderingOrderByIsPresent(searchDebtorRequest).ifPresent(errors::add);
            validateOrderingOrderByType(searchDebtorRequest).ifPresent(errors::add);
            validateOrderingOrderDirectionIsPresent(searchDebtorRequest).ifPresent(errors::add);
            validateOrderingOrderDirectionType(searchDebtorRequest).ifPresent(errors::add);
        }

        if (searchDebtorRequest.getPaging() != null) {
            if (searchDebtorRequest.getPaging().getPageSize() != null && searchDebtorRequest.getPaging().getPageNumber() != null) {
                validatePagingPageNumber(searchDebtorRequest).ifPresent(errors::add);
                validatePagingPageSize(searchDebtorRequest).ifPresent(errors::add);
            }
            validatePagingPageSizeIsPresent(searchDebtorRequest).ifPresent(errors::add);
            validatePagingPageNumberIsPresent(searchDebtorRequest).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validateSearchParameters(SearchDebtorRequest searchDebtorRequest) {
        return isEmpty(searchDebtorRequest.getName()) && isEmpty(searchDebtorRequest.getListItem())
                ? Optional.of(new CoreError("Debtor name and List item", "cannot both be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateOrderingOrderByIsPresent(SearchDebtorRequest searchDebtorRequest) {
        return searchDebtorRequest.getOrdering().getOrderBy().equals(OrderingType.EMPTY) && !isEmpty(searchDebtorRequest.getOrdering().getOrderDirection().getDirection())
                ? Optional.of(new CoreError("OrderBy", "Cannot be empty if OrderDirection is provided!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateOrderingOrderDirectionIsPresent(SearchDebtorRequest searchDebtorRequest) {
        return !isEmpty(searchDebtorRequest.getOrdering().getOrderBy().getType()) && searchDebtorRequest.getOrdering().getOrderDirection().equals(OrderingDirection.EMPTY)
                ? Optional.of(new CoreError("OrderDirection", "Cannot be empty if OrderBy is provided!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateOrderingOrderByType (SearchDebtorRequest searchDebtorRequest) {
        return searchDebtorRequest.getOrdering().getOrderBy().equals(OrderingType.INVALID)
                ? Optional.of(new CoreError("OrderBy", "Invalid Ordering Type!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateOrderingOrderDirectionType (SearchDebtorRequest searchDebtorRequest) {
        return searchDebtorRequest.getOrdering().getOrderDirection().equals(OrderingDirection.INVALID)
                ? Optional.of(new CoreError("OrderDirection", "Invalid Ordering Direction Type!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePagingPageNumber(SearchDebtorRequest searchDebtorRequest) {
        return searchDebtorRequest.getPaging().getPageNumber() <= 0
                ? Optional.of(new CoreError("PageNumber", "Must be greater than zero!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePagingPageSize(SearchDebtorRequest searchDebtorRequest) {
        return searchDebtorRequest.getPaging().getPageSize() <= 0
                ? Optional.of(new CoreError("PageSize", "Must be greater than zero!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePagingPageSizeIsPresent(SearchDebtorRequest searchDebtorRequest) {
        return !isNull(searchDebtorRequest.getPaging().getPageSize()) && isNull(searchDebtorRequest.getPaging().getPageNumber())
                ? Optional.of(new CoreError("PageSize", "Cannot be empty if PageNumber is provided!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePagingPageNumberIsPresent(SearchDebtorRequest searchDebtorRequest) {
        return isNull(searchDebtorRequest.getPaging().getPageSize()) && !isNull(searchDebtorRequest.getPaging().getPageNumber())
                ? Optional.of(new CoreError("PageNumber", "Cannot be empty if PageSize is provided!"))
                : Optional.empty();
    }

    private boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }

    private boolean isNull(Integer integer) {
        return integer == null;
    }
}
