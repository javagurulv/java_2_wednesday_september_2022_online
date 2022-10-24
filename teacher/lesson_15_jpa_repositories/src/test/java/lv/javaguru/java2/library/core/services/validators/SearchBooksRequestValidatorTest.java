package lv.javaguru.java2.library.core.services.validators;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import lv.javaguru.java2.library.core.requests.Ordering;
import lv.javaguru.java2.library.core.requests.Paging;
import lv.javaguru.java2.library.core.requests.SearchBooksRequest;
import lv.javaguru.java2.library.core.responses.CoreError;

public class SearchBooksRequestValidatorTest {

	private SearchBooksRequestValidator validator = new SearchBooksRequestValidator();

	@Test
	public void shouldNotReturnErrorsWhenTitleIsProvided() {
		SearchBooksRequest request = new SearchBooksRequest("Title", null);
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 0);
	}

	@Test
	public void shouldNotReturnErrorsWhenAuthorIsProvided() {
		SearchBooksRequest request = new SearchBooksRequest(null, "Author");
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 0);
	}

	@Test
	public void shouldNotReturnErrorsWhenTitleAndAuthorIsProvided() {
		SearchBooksRequest request = new SearchBooksRequest("Title", "Author");
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 0);
	}

	@Test
	public void shouldReturnErrorWhenSearchFieldsAreEmpty() {
		SearchBooksRequest request = new SearchBooksRequest(null, null);
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 2);
		assertEquals(errors.get(0).getField(), "title");
		assertEquals(errors.get(0).getMessage(), "Must not be empty!");
		assertEquals(errors.get(1).getField(), "author");
		assertEquals(errors.get(1).getMessage(), "Must not be empty!");
	}

	@Test
	public void shouldReturnErrorWhenOrderDirectionAreEmpty() {
		Ordering ordering = new Ordering("title", null);
		SearchBooksRequest request = new SearchBooksRequest("Title", "Author", ordering);
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getField(), "orderDirection");
		assertEquals(errors.get(0).getMessage(), "Must not be empty!");
	}

	@Test
	public void shouldReturnErrorWhenOrderByAreEmpty() {
		Ordering ordering = new Ordering(null, "ASCENDING");
		SearchBooksRequest request = new SearchBooksRequest("Title", "Author", ordering);
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getField(), "orderBy");
		assertEquals(errors.get(0).getMessage(), "Must not be empty!");
	}

	@Test
	public void shouldReturnErrorWhenOrderByContainNotValidValue() {
		Ordering ordering = new Ordering("notValidValue", "ASCENDING");
		SearchBooksRequest request = new SearchBooksRequest("Title", "Author", ordering);
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getField(), "orderBy");
		assertEquals(errors.get(0).getMessage(), "Must contain 'author' or 'title' only!");
	}

	@Test
	public void shouldReturnErrorWhenOrderDirectionContainNotValidValue() {
		Ordering ordering = new Ordering("title", "notValidValue");
		SearchBooksRequest request = new SearchBooksRequest("Title", "Author", ordering);
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getField(), "orderDirection");
		assertEquals(errors.get(0).getMessage(), "Must contain 'ASCENDING' or 'DESCENDING' only!");
	}

	@Test
	public void shouldReturnErrorWhenPageNumberContainNotValidValue() {
		Paging paging = new Paging(0, 1);
		SearchBooksRequest request = new SearchBooksRequest("Title", "Author", paging);
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getField(), "pageNumber");
		assertEquals(errors.get(0).getMessage(), "Must be greater then 0!");
	}

	@Test
	public void shouldReturnErrorWhenPageSizeContainNotValidValue() {
		Paging paging = new Paging(1, 0);
		SearchBooksRequest request = new SearchBooksRequest("Title", "Author", paging);
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getField(), "pageSize");
		assertEquals(errors.get(0).getMessage(), "Must be greater then 0!");
	}

	@Test
	public void shouldReturnErrorWhenPageNumberAreEmpty() {
		Paging paging = new Paging(null, 1);
		SearchBooksRequest request = new SearchBooksRequest("Title", "Author", paging);
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getField(), "pageNumber");
		assertEquals(errors.get(0).getMessage(), "Must not be empty!");
	}

	@Test
	public void shouldReturnErrorWhenPageSizeAreEmpty() {
		Paging paging = new Paging(1, null);
		SearchBooksRequest request = new SearchBooksRequest("Title", "Author", paging);
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getField(), "pageSize");
		assertEquals(errors.get(0).getMessage(), "Must not be empty!");
	}

}