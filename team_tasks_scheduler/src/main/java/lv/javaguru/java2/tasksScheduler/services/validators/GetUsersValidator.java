package lv.javaguru.java2.tasksScheduler.services.validators;

import lv.javaguru.java2.tasksScheduler.enums.MenuType;
import lv.javaguru.java2.tasksScheduler.requests.GetUsersRequest;
import lv.javaguru.java2.tasksScheduler.requests.ordering_paging.Ordering;
import lv.javaguru.java2.tasksScheduler.requests.ordering_paging.Paging;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetUsersValidator {

    public List<CoreError> validate(GetUsersRequest request, MenuType menuType) {
        List<CoreError> errors = new ArrayList<>();
        if (request.getOrdering() != null) {
            validateOrderBy(request.getOrdering(), menuType).ifPresent(errors::add);
            validateOrderDirection(request.getOrdering()).ifPresent(errors::add);
            validateMandatoryOrderBy(request.getOrdering()).ifPresent(errors::add);
            validateMandatoryOrderDirection(request.getOrdering()).ifPresent(errors::add);
        }
        if (request.getPaging() != null) {
            validatePageNumber(request.getPaging()).ifPresent(errors::add);
            validatePageSize(request.getPaging()).ifPresent(errors::add);
            validateMandatoryPageNumber(request.getPaging()).ifPresent(errors::add);
            validateMandatoryPageSize(request.getPaging()).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validateOrderBy(Ordering ordering, MenuType menuType) {
        if (menuType == MenuType.ADMIN) {
            return (ordering.getOrderBy() != null
                    && !(ordering.getOrderBy().equals("username") || ordering.getOrderBy().equals("email")))
                    ? Optional.of(new CoreError("OrderBy", "Must contain 'username' or 'email' only!"))
                    : Optional.empty();
        }
        if (menuType == MenuType.START) {
            return (ordering.getOrderBy() != null && !ordering.getOrderBy().equals("username"))
                    ? Optional.of(new CoreError("Order By", "Must contain 'username' only!"))
                    : Optional.empty();
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateOrderDirection(Ordering ordering) {
        return (ordering.getOrderDirection() != null
                && !(ordering.getOrderDirection().equals("ASCENDING") || ordering.getOrderDirection().equals("DESCENDING")))
                ? Optional.of(new CoreError("Order Direction", "Must contain 'ASCENDING' or 'DESCENDING' only!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryOrderBy(Ordering ordering) {
        return (ordering.getOrderDirection() != null && ordering.getOrderBy() == null)
                ? Optional.of(new CoreError("Order By", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryOrderDirection(Ordering ordering) {
        return (ordering.getOrderBy() != null && ordering.getOrderDirection() == null)
                ? Optional.of(new CoreError("Order Direction", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePageNumber(Paging paging) {
        return (paging.getPageNumber() != null
                && paging.getPageNumber() <= 0)
                ? Optional.of(new CoreError("Page Number", "Must be greater then 0!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePageSize(Paging paging) {
        return (paging.getPageSize() != null
                && paging.getPageSize() <= 0)
                ? Optional.of(new CoreError("Page Size", "Must be greater then 0!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryPageNumber(Paging paging) {
        return (paging.getPageNumber() == null && paging.getPageSize() != null)
                ? Optional.of(new CoreError("Page Number", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryPageSize(Paging paging) {
        return (paging.getPageSize() == null && paging.getPageNumber() != null)
                ? Optional.of(new CoreError("Page Size", "Must not be empty!"))
                : Optional.empty();
    }
}
