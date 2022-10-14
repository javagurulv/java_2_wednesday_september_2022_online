package lv.javaguru.java2.library.core.services.validators;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import lv.javaguru.java2.library.core.requests.AddBookRequest;
import lv.javaguru.java2.library.core.responses.CoreError;

public class AddBookRequestValidatorTest {

	private AddBookRequestValidator validator = new AddBookRequestValidator();

	@Test
	public void shouldReturnErrorWhenTitleIsNull() {
		AddBookRequest request = new AddBookRequest(null, "Author");
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getField(), "title");
		assertEquals(errors.get(0).getMessage(), "Must not be empty!");
	}

	@Test
	public void shouldReturnErrorWhenAuthorIsNull() {
		AddBookRequest request = new AddBookRequest("Title", null);
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getField(), "author");
		assertEquals(errors.get(0).getMessage(), "Must not be empty!");
	}

	@Test
	public void shouldReturnErrorsWhenAuthorAndTitleIsNull() {
		AddBookRequest request = new AddBookRequest(null, null);
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 2);
	}

	@Test
	public void shouldSuccess() {
		AddBookRequest request = new AddBookRequest("Title", "Author");
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 0);
	}

}