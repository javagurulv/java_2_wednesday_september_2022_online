package lv.javaguru.java2.repo_men_inc.core.validators;

import lv.javaguru.java2.repo_men_inc.core.requests.*;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SearchDebtorValidatorTest {

    SearchDebtorValidator searchDebtorValidator = new SearchDebtorValidator();

    @Test
    public void shouldReturnErrorIfNAmeAndItemListAreMissing() {
        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest(null, null, null, null);
        List<CoreError> errors = searchDebtorValidator.validate(searchDebtorRequest);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "Debtor name and List item");
        assertEquals(errors.get(0).getMessage(), "cannot both be empty!");
    }

    @Test
    public void shouldReturnErrorsIfOrderingOrderByIsPresentAndDirectionIsMissing() {
        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest("name", "item", new Ordering(OrderingType.EMPTY, OrderingDirection.DESC), null);
        List<CoreError> errors = searchDebtorValidator.validate(searchDebtorRequest);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "OrderBy");
        assertEquals(errors.get(0).getMessage(), "Cannot be empty if OrderDirection is provided!");
    }

    @Test
    public void shouldReturnErrorsIfOrderingOrderByIsMissingAndDirectionIsPresent() {
        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest("name", "item", new Ordering(OrderingType.NAME, OrderingDirection.EMPTY), null);
        List<CoreError> errors = searchDebtorValidator.validate(searchDebtorRequest);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "OrderDirection");
        assertEquals(errors.get(0).getMessage(), "Cannot be empty if OrderBy is provided!");
    }

    @Test
    public void shouldReturnErrorsIfOrderingTypeIsInvalid() {
        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest("name", "item", new Ordering(OrderingType.INVALID, OrderingDirection.DESC), null);
        List<CoreError> errors = searchDebtorValidator.validate(searchDebtorRequest);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "OrderBy");
        assertEquals(errors.get(0).getMessage(), "Invalid Ordering Type!");
    }

    @Test
    public void shouldReturnErrorsIfOrderingDirectionIsInvalid() {
        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest("name", "item", new Ordering(OrderingType.NAME, OrderingDirection.INVALID), null);
        List<CoreError> errors = searchDebtorValidator.validate(searchDebtorRequest);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "OrderDirection");
        assertEquals(errors.get(0).getMessage(), "Invalid Ordering Direction Type!");
    }

    @Test
    public void shouldReturnErrorsIfPagingPageNumberIsEqualToZero() {
        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest("name", "item", null, new Paging(0, 10));
        List<CoreError> errors = searchDebtorValidator.validate(searchDebtorRequest);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "PageNumber");
        assertEquals(errors.get(0).getMessage(), "Must be greater than zero!");
    }

    @Test
    public void shouldReturnErrorsIfPagingPageNumberIsLessThanZero() {
        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest("name", "item", null, new Paging(-1, 10));
        List<CoreError> errors = searchDebtorValidator.validate(searchDebtorRequest);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "PageNumber");
        assertEquals(errors.get(0).getMessage(), "Must be greater than zero!");
    }

    @Test
    public void shouldReturnErrorsIfPagingPageSizeIsEqualToZero() {
        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest("name", "item", null, new Paging(1, 0));
        List<CoreError> errors = searchDebtorValidator.validate(searchDebtorRequest);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "PageSize");
        assertEquals(errors.get(0).getMessage(), "Must be greater than zero!");
    }

    @Test
    public void shouldReturnErrorsIfPagingPageSizeIsLessThanZero() {
        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest("name", "item", null, new Paging(1, -1));
        List<CoreError> errors = searchDebtorValidator.validate(searchDebtorRequest);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "PageSize");
        assertEquals(errors.get(0).getMessage(), "Must be greater than zero!");
    }

    @Test
    public void shouldReturnErrorsIfPagingPageNumberIsMissing() {
        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest("name", "item", null, new Paging(null, 10));
        List<CoreError> errors = searchDebtorValidator.validate(searchDebtorRequest);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "PageSize");
        assertEquals(errors.get(0).getMessage(), "Cannot be empty if PageNumber is provided!");
    }

    @Test
    public void shouldReturnErrorsIfPagingPageSizeIsMissing() {
        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest("name", "item", null, new Paging(1, null));
        List<CoreError> errors = searchDebtorValidator.validate(searchDebtorRequest);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "PageNumber");
        assertEquals(errors.get(0).getMessage(), "Cannot be empty if PageSize is provided!");
    }
}