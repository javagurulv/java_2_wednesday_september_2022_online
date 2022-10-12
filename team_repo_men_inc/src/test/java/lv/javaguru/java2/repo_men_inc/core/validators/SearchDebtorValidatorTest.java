package lv.javaguru.java2.repo_men_inc.core.validators;

import lv.javaguru.java2.repo_men_inc.core.requests.*;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class SearchDebtorValidatorTest {

    SearchDebtorFieldValidator searchDebtorFieldValidator;
    OrderingValidator orderingValidator;
    PagingValidator pagingValidator;
    SearchDebtorValidator searchDebtorValidator;

    @Before
    public void init() {
        searchDebtorFieldValidator = Mockito.mock(SearchDebtorFieldValidator.class);
        orderingValidator = Mockito.mock(OrderingValidator.class);
        pagingValidator = Mockito.mock(PagingValidator.class);
        searchDebtorValidator = new SearchDebtorValidator(pagingValidator, orderingValidator, searchDebtorFieldValidator);
    }

    @Test
    public void shouldNotReturnErrorsWhenFieldValidatorReturnsErrors () {
        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest("name", null, null, null);
        when(searchDebtorFieldValidator.validate(searchDebtorRequest)).thenReturn(List.of());
        List<CoreError> errors = searchDebtorValidator.validate(searchDebtorRequest);
        assertEquals(0, errors.size());
    }

    @Test
    public void shouldReturnErrorsWhenFieldValidatorReturnsErrors () {
        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest(null, null, null, null);
        CoreError error = new CoreError("Debtor name and List item", "cannot both be empty!");
        when(searchDebtorFieldValidator.validate(searchDebtorRequest)).thenReturn(List.of(error));
        List<CoreError> errors = searchDebtorValidator.validate(searchDebtorRequest);
        assertEquals(1, errors.size());
    }

    @Test
    public void shouldNotReturnErrorsWhenOrderingValidatorReturnNoErrors() {
        Ordering ordering = new Ordering(OrderingType.NAME, OrderingDirection.ASC);
        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest("name", null, ordering, null);
        when(orderingValidator.validate(ordering)).thenReturn(List.of());
        List<CoreError> errors = searchDebtorValidator.validate(searchDebtorRequest);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorsWhenOrderingValidatorReturnErrors() {
        Ordering ordering = new Ordering(OrderingType.EMPTY, OrderingDirection.ASC);
        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest("name", null, ordering, null);
        CoreError error = new CoreError("orderBy", "Must not be empty!");
        when(orderingValidator.validate(ordering)).thenReturn(List.of(error));
        List<CoreError> errors = searchDebtorValidator.validate(searchDebtorRequest);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldNotInvokeOrderingValidatorWhenNoOrderingObjectPresentInRequest() {
        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest("name", null, null, null);
        searchDebtorValidator.validate(searchDebtorRequest);
        verifyNoMoreInteractions(orderingValidator);
    }

    @Test
    public void shouldNotReturnErrorsWhenPagingValidatorReturnNoErrors() {
        Paging paging = new Paging(10, 10);
        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest("name", null, null, paging);
        when(pagingValidator.validate(paging)).thenReturn(List.of());
        List<CoreError> errors = searchDebtorValidator.validate(searchDebtorRequest);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorsWhenPagingValidatorReturnErrors() {
        Paging paging = new Paging(null, 10);
        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest("name", null, null, paging);
        CoreError error = new CoreError("pageNumber", "Must not be empty!");
        when(pagingValidator.validate(paging)).thenReturn(List.of(error));
        List<CoreError> errors = searchDebtorValidator.validate(searchDebtorRequest);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageNumber");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldNotInvokePagingValidatorWhenNoPagingObjectPresentInRequest() {
        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest("name", null, null, null);
        searchDebtorValidator.validate(searchDebtorRequest);
        verifyNoMoreInteractions(pagingValidator);
    }

}