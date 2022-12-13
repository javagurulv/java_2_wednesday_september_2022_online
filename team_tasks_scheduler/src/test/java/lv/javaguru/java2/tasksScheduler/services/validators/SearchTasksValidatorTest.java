package lv.javaguru.java2.tasksScheduler.services.validators;

import lv.javaguru.java2.tasksScheduler.core.requests.SearchTasksRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.ordering_paging.Ordering;
import lv.javaguru.java2.tasksScheduler.core.requests.ordering_paging.Paging;
import lv.javaguru.java2.tasksScheduler.core.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.core.services.validators.SearchTasksValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SearchTasksValidatorTest {

    private SearchTasksValidator validator = new SearchTasksValidator();

    @Test
    public void shouldReturnErrorWhenSearchPhraseIsNull() {
        SearchTasksRequest request = new SearchTasksRequest(null,
                new Ordering("description", "ascending"));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Search phrase");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenSearchPhraseIsEmpty() {
        SearchTasksRequest request = new SearchTasksRequest(" ",
                new Ordering("description", "ascending"));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Search phrase");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderByIsEmpty() {
        SearchTasksRequest request = new SearchTasksRequest("abc",
                new Ordering(" ", "ascending"));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Order By");
        assertEquals(errors.get(0).getMessage(), "Must contain 'description'," +
                "'end date', 'due date' only!");
    }

    @Test
    public void shouldReturnErrorWhenOrderByIsWrong() {
        SearchTasksRequest request = new SearchTasksRequest("abc",
                new Ordering("wrong", "ascending"));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Order By");
        assertEquals(errors.get(0).getMessage(), "Must contain 'description'," +
                "'end date', 'due date' only!");
    }

    @Test
    public void shouldSuccessWhenOrderingByDescription() {
        SearchTasksRequest request = new SearchTasksRequest("abc",
                new Ordering("description", "ascending"));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldSuccessWhenOrderingByEndDate() {
        SearchTasksRequest request = new SearchTasksRequest("abc",
                new Ordering("end date", "ascending"));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldSuccessWhenOrderingByDueDate() {
        SearchTasksRequest request = new SearchTasksRequest("abc",
                new Ordering("due date", "ascending"));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenOrderDirectionIsEmpty() {
        SearchTasksRequest request = new SearchTasksRequest("abc",
                new Ordering("description", " "));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Order Direction");
        assertEquals(errors.get(0).getMessage(), "Must contain 'ascending' or 'descending' only!");
    }

    @Test
    public void shouldReturnErrorWhenOrderDirectionIsWrong() {
        SearchTasksRequest request = new SearchTasksRequest("abc",
                new Ordering("description", "wrong"));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Order Direction");
        assertEquals(errors.get(0).getMessage(), "Must contain 'ascending' or 'descending' only!");
    }

    @Test
    public void shouldSuccessWhenOrderDirectionIsAscending() {
        SearchTasksRequest request = new SearchTasksRequest("abc",
                new Ordering("description", "ascending"));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldSuccessWhenOrderDirectionIsDescending() {
        SearchTasksRequest request = new SearchTasksRequest("abc",
                new Ordering("description", "descending"));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenOrderByIsNullButOrderDirectionProvided() {
        SearchTasksRequest request = new SearchTasksRequest("abc",
                new Ordering(null, "ascending"));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Order By");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderDirectionIsNullButOrderByProvided() {
        SearchTasksRequest request = new SearchTasksRequest("abc",
                new Ordering("description", null));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Order Direction");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPageNumberIsZero() {
        SearchTasksRequest request = new SearchTasksRequest("abc",
                new Paging(0, 1));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Page Number");
        assertEquals(errors.get(0).getMessage(), "Must be greater then 0!");
    }

    @Test
    public void shouldReturnErrorWhenPageNumberIsLessThanZero() {
        SearchTasksRequest request = new SearchTasksRequest("abc",
                new Paging(-1, 1));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Page Number");
        assertEquals(errors.get(0).getMessage(), "Must be greater then 0!");
    }

    @Test
    public void shouldSuccessWhenPageNumberIsValid() {
        SearchTasksRequest request = new SearchTasksRequest("abc",
                new Paging(1, 1));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenPageSizeIsZero() {
        SearchTasksRequest request = new SearchTasksRequest("abc",
                new Paging(1, 0));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Page Size");
        assertEquals(errors.get(0).getMessage(), "Must be greater then 0!");
    }

    @Test
    public void shouldReturnErrorWhenPageSizeIsLessThanZero() {
        SearchTasksRequest request = new SearchTasksRequest("abc",
                new Paging(1, -1));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Page Size");
        assertEquals(errors.get(0).getMessage(), "Must be greater then 0!");
    }

    @Test
    public void shouldSuccessWhenPageSizeIsValid() {
        SearchTasksRequest request = new SearchTasksRequest("abc",
                new Paging(1, 1));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenPageNumberIsNullButPageSizeProvided() {
        SearchTasksRequest request = new SearchTasksRequest("abc",
                new Paging(null, 1));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Page Number");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPageSizeIsNullButPageNumberProvided() {
        SearchTasksRequest request = new SearchTasksRequest("abc",
                new Paging(1, null));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Page Size");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }
}
