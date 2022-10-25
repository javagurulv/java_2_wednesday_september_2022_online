package lv.javaguru.java2.library.core.services;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import lv.javaguru.java2.library.core.database.BookRepository;
import lv.javaguru.java2.library.core.requests.AddBookRequest;
import lv.javaguru.java2.library.core.responses.AddBookResponse;
import lv.javaguru.java2.library.core.responses.CoreError;
import lv.javaguru.java2.library.core.services.validators.AddBookRequestValidator;
import lv.javaguru.java2.library.core.matchers.BookMatcher;

@RunWith(MockitoJUnitRunner.class)
public class AddBookServiceTest {

	@Mock private BookRepository bookRepository;
	@Mock private AddBookRequestValidator validator;
	@InjectMocks private AddBookService service;

	@Test
	public void shouldReturnResponseWithErrorsWhenValidationFails() {
		AddBookRequest notValidRequest = new AddBookRequest(null, "Author");
		when(validator.validate(notValidRequest)).thenReturn(List.of(new CoreError("title", "Must not be empty!")));
		AddBookResponse response = service.execute(notValidRequest);
		assertTrue(response.hasErrors());
	}

	@Test
	public void shouldReturnResponseWithErrorsReceivedFromValidator() {
		AddBookRequest notValidRequest = new AddBookRequest(null, "Author");
		when(validator.validate(notValidRequest)).thenReturn(List.of(new CoreError("title", "Must not be empty!")));
		AddBookResponse response = service.execute(notValidRequest);
		assertEquals(response.getErrors().size(), 1);
		assertEquals(response.getErrors().get(0).getField(), "title");
		assertEquals(response.getErrors().get(0).getMessage(), "Must not be empty!");
	}

	@Test
	public void shouldNotInvokeDatabaseWhenRequestValidationFails() {
		AddBookRequest notValidRequest = new AddBookRequest(null, "Author");
		when(validator.validate(notValidRequest)).thenReturn(List.of(new CoreError("title", "Must not be empty!")));
		service.execute(notValidRequest);
		verifyNoInteractions(bookRepository);
	}

	@Test
	public void shouldAddBookToDatabaseWhenRequestIsValid() {
		AddBookRequest validRequest = new AddBookRequest("Title", "Author");
		when(validator.validate(validRequest)).thenReturn(List.of());
		service.execute(validRequest);
		verify(bookRepository).save(argThat(new BookMatcher("Title", "Author")));
	}

	@Test
	public void shouldReturnResponseWithoutErrorsWhenRequestIsValid() {
		AddBookRequest validRequest = new AddBookRequest("Title", "Author");
		when(validator.validate(validRequest)).thenReturn(List.of());
		AddBookResponse response = service.execute(validRequest);
		assertFalse(response.hasErrors());
	}

	@Test
	public void shouldReturnResponseWithBookWhenRequestIsValid() {
		AddBookRequest validRequest = new AddBookRequest("Title", "Author");
		when(validator.validate(validRequest)).thenReturn(List.of());
		AddBookResponse response = service.execute(validRequest);
		assertNotNull(response.getNewBook());
		assertEquals(response.getNewBook().getTitle(), validRequest.getTitle());
		assertEquals(response.getNewBook().getAuthor(), validRequest.getAuthor());
	}

}