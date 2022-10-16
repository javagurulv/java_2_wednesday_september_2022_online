package lv.javaguru.java2.library.core.services;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import lv.javaguru.java2.library.Book;
import lv.javaguru.java2.library.core.database.Database;
import lv.javaguru.java2.library.core.requests.AddBookRequest;
import lv.javaguru.java2.library.core.responses.AddBookResponse;
import lv.javaguru.java2.library.core.responses.CoreError;
import lv.javaguru.java2.library.core.services.validators.AddBookRequestValidator;
import lv.javaguru.java2.library.core.services.validators.AddBookRequestValidatorMock;
import lv.javaguru.java2.library.matchers.BookMatcher;

@RunWith(MockitoJUnitRunner.class)
public class AddBookServiceTest {

	@Mock private Database database;
	@Mock private AddBookRequestValidator validator;
	@InjectMocks private AddBookService service;


	@Before
	public void init() {
		database = Mockito.mock(Database.class);
		validator = Mockito.mock(AddBookRequestValidator.class);
		service = new AddBookService(database, validator);
	}

	@Test
	public void testMyMock() {
		validator = Mockito.mock(AddBookRequestValidatorMock.class);

		AddBookRequest request1 = new AddBookRequest("Title", "Au");
		AddBookRequest request2 = new AddBookRequest("Title", "Au");
		validator = Mockito.mock(AddBookRequestValidator.class);
		//Mockito.when(validator.validate(request1)).thenReturn(List.of());
		/*Mockito.when(validator.validate(request2)).thenReturn(List.of(
				new CoreError("title", "errorMesage")
		));*/

		Mockito.when(validator.validate(any())).thenReturn(List.of());

		List<CoreError> errors = validator.validate(request2);

		database = Mockito.mock(Database.class);

		Book book = new Book("as", "sfd");

		database.save(book);

		//Mockito.verify(database).save(book);
		//Mockito.verify(database, times(1)).save(book);
		//Mockito.verify(database, times(2)).save(book);

		//Mockito.verify(database, times(0)).deleteById(1L);

		///Mockito.verifyNoInteractions(database);

		service = new AddBookService(
				database, new AddBookRequestValidatorMock()
		);
	}

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
		verifyNoInteractions(database);
	}

	@Test
	public void shouldAddBookToDatabaseWhenRequestIsValid() {
		AddBookRequest validRequest = new AddBookRequest("Title", "Author");
		when(validator.validate(validRequest)).thenReturn(List.of());
		service.execute(validRequest);
		verify(database).save(argThat(new BookMatcher("Title", "Author")));
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