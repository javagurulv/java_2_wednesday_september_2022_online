package lv.javaguru.java2.library.core.services.validators;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import lv.javaguru.java2.library.core.requests.Ordering;
import lv.javaguru.java2.library.core.requests.Paging;
import lv.javaguru.java2.library.core.requests.SearchBooksRequest;
import lv.javaguru.java2.library.core.responses.CoreError;

public class SearchBooksRequestValidatorTest {

	private SearchBooksRequestFieldValidator fieldValidator;
	private OrderingValidator orderingValidator;
	private PagingValidator pagingValidator;
	private SearchBooksRequestValidator validator;

	@Before
	public void init() {
		fieldValidator = Mockito.mock(SearchBooksRequestFieldValidator.class);
		orderingValidator = Mockito.mock(OrderingValidator.class);
		pagingValidator = Mockito.mock(PagingValidator.class);
		validator = new SearchBooksRequestValidator(fieldValidator, orderingValidator, pagingValidator);
	}

	@Test
	public void shouldNotReturnErrorsWhenFieldValidatorReturnNoErrors() {
		SearchBooksRequest request = new SearchBooksRequest("Title", null);
		when(fieldValidator.validate(request)).thenReturn(List.of());
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 0);
	}

	@Test
	public void shouldReturnErrorsWhenFieldValidatorReturnErrors() {
		SearchBooksRequest request = new SearchBooksRequest(null, "Author");
		CoreError error = new CoreError("title", "Must not be empty!");
		when(fieldValidator.validate(request)).thenReturn(List.of(error));
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getField(), "title");
		assertEquals(errors.get(0).getMessage(), "Must not be empty!");
	}

	@Test
	public void shouldNotReturnErrorsWhenOrderingValidatorReturnNoErrors() {
		Ordering ordering = new Ordering("title", "ASC");
		SearchBooksRequest request = new SearchBooksRequest("Title", "Author", ordering);
		when(orderingValidator.validate(ordering)).thenReturn(List.of());
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 0);
	}

	@Test
	public void shouldReturnErrorsWhenOrderingValidatorReturnErrors() {
		Ordering ordering = new Ordering(null, "ASCENDING");
		SearchBooksRequest request = new SearchBooksRequest("Title", "Author", ordering);
		CoreError error = new CoreError("orderBy", "Must not be empty!");
		when(orderingValidator.validate(ordering)).thenReturn(List.of(error));
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getField(), "orderBy");
		assertEquals(errors.get(0).getMessage(), "Must not be empty!");
	}

	@Test
	public void shouldNotInvokeOrderingValidatorWhenNoOrderingObjectPresentInRequest() {
		SearchBooksRequest request = new SearchBooksRequest("Title", "Author");
		validator.validate(request);
		verifyNoMoreInteractions(orderingValidator);
	}

	@Test
	public void shouldNotReturnErrorsWhenPagingValidatorReturnNoErrors() {
		Paging paging = new Paging(10, 10);
		SearchBooksRequest request = new SearchBooksRequest("Title", "Author", paging);
		when(pagingValidator.validate(paging)).thenReturn(List.of());
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 0);
	}

	@Test
	public void shouldReturnErrorsWhenPagingValidatorReturnErrors() {
		Paging paging = new Paging(null, 10);
		SearchBooksRequest request = new SearchBooksRequest("Title", "Author", paging);
		CoreError error = new CoreError("pageNumber", "Must not be empty!");
		when(pagingValidator.validate(paging)).thenReturn(List.of(error));
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getField(), "pageNumber");
		assertEquals(errors.get(0).getMessage(), "Must not be empty!");
	}

	@Test
	public void shouldNotInvokePagingValidatorWhenNoPagingObjectPresentInRequest() {
		SearchBooksRequest request = new SearchBooksRequest("Title", "Author");
		validator.validate(request);
		verifyNoMoreInteractions(pagingValidator);
	}

}