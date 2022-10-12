package lv.javaguru.java2.repo_men_inc.core.validators;

import lv.javaguru.java2.repo_men_inc.core.requests.Ordering;
import lv.javaguru.java2.repo_men_inc.core.requests.OrderingDirection;
import lv.javaguru.java2.repo_men_inc.core.requests.OrderingType;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderingValidatorTest {

    OrderingValidator orderingValidator = new OrderingValidator();

    @Test
    public void shouldReturnErrorsIfOrderingOrderByIsPresentAndDirectionIsMissing() {
        Ordering ordering = new Ordering(OrderingType.EMPTY, OrderingDirection.DESC);
        List<CoreError> errors = orderingValidator.validate(ordering);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "OrderBy");
        assertEquals(errors.get(0).getMessage(), "Cannot be empty if OrderDirection is provided!");
    }

    @Test
    public void shouldReturnErrorsIfOrderingOrderByIsMissingAndDirectionIsPresent() {
        Ordering ordering = new Ordering(OrderingType.NAME, OrderingDirection.EMPTY);
        List<CoreError> errors = orderingValidator.validate(ordering);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "OrderDirection");
        assertEquals(errors.get(0).getMessage(), "Cannot be empty if OrderBy is provided!");
    }

    @Test
    public void shouldReturnErrorsIfOrderingTypeIsInvalid() {
        Ordering ordering = new Ordering(OrderingType.INVALID, OrderingDirection.DESC);
        List<CoreError> errors = orderingValidator.validate(ordering);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "OrderBy");
        assertEquals(errors.get(0).getMessage(), "Invalid Ordering Type!");
    }

    @Test
    public void shouldReturnErrorsIfOrderingDirectionIsInvalid() {
        Ordering ordering = new Ordering(OrderingType.NAME, OrderingDirection.INVALID);
        List<CoreError> errors = orderingValidator.validate(ordering);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "OrderDirection");
        assertEquals(errors.get(0).getMessage(), "Invalid Ordering Direction Type!");
    }

}