package lv.javaguru.java2.library.core.services.validators;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import lv.javaguru.java2.library.core.requests.SearchBooksRequest;
import lv.javaguru.java2.library.core.responses.CoreError;

public class SearchBooksRequestFieldValidatorTest {

	private SearchBooksRequestFieldValidator validator = new SearchBooksRequestFieldValidator();

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

}