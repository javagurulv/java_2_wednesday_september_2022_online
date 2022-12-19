package lv.javaguru.java2.repo_men_inc.core.validators;

import lv.javaguru.java2.repo_men_inc.core.requests.Ordering;
import lv.javaguru.java2.repo_men_inc.core.requests.OrderingDirection;
import lv.javaguru.java2.repo_men_inc.core.requests.OrderingType;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class OrderingValidator {
    public List<CoreError> validate(Ordering ordering) {
        List<CoreError> errors = new ArrayList<>();
        validateOrderingOrderByIsPresent(ordering).ifPresent(errors::add);
        validateOrderingOrderByType(ordering).ifPresent(errors::add);
        validateOrderingOrderDirectionIsPresent(ordering).ifPresent(errors::add);
        validateOrderingOrderDirectionType(ordering).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateOrderingOrderByIsPresent(Ordering ordering) {
        return ordering.getOrderBy().equals(OrderingType.EMPTY) && isNotEmpty(ordering.getOrderDirection().getDirection())
                ? Optional.of(new CoreError("OrderBy", "Cannot be empty if OrderDirection is provided!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateOrderingOrderDirectionIsPresent(Ordering ordering) {
        return isNotEmpty(ordering.getOrderBy().getType()) && ordering.getOrderDirection().equals(OrderingDirection.EMPTY)
                ? Optional.of(new CoreError("OrderDirection", "Cannot be empty if OrderBy is provided!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateOrderingOrderByType (Ordering ordering) {
        return ordering.getOrderBy().equals(OrderingType.INVALID)
                ? Optional.of(new CoreError("OrderBy", "Invalid Ordering Type!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateOrderingOrderDirectionType (Ordering ordering) {
        return ordering.getOrderDirection().equals(OrderingDirection.INVALID)
                ? Optional.of(new CoreError("OrderDirection", "Invalid Ordering Direction Type!"))
                : Optional.empty();
    }

    private boolean isNotEmpty(String string) {
        return string != null && !string.isEmpty();
    }
}
