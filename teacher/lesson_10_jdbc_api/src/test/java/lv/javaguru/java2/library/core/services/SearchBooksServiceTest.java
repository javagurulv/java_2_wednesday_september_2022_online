package lv.javaguru.java2.library.core.services;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import lv.javaguru.java2.library.core.domain.Book;
import lv.javaguru.java2.library.core.database.Database;
import lv.javaguru.java2.library.core.requests.Ordering;
import lv.javaguru.java2.library.core.requests.Paging;
import lv.javaguru.java2.library.core.requests.SearchBooksRequest;
import lv.javaguru.java2.library.core.responses.CoreError;
import lv.javaguru.java2.library.core.responses.SearchBooksResponse;
import lv.javaguru.java2.library.core.services.validators.SearchBooksRequestValidator;

@RunWith(MockitoJUnitRunner.class)
public class SearchBooksServiceTest {

	@Mock private Database database;
	@Mock private SearchBooksRequestValidator validator;
	@InjectMocks private SearchBooksService service;

	@Before
	public void setup() {
		ReflectionTestUtils.setField(service, "orderingEnabled", true);
		ReflectionTestUtils.setField(service, "pagingEnabled", true);
	}

	@Test
	public void shouldReturnResponseWithErrorsWhenValidatorFails() {
		SearchBooksRequest request = new SearchBooksRequest(null, null);
		List<CoreError> errors = new ArrayList<>();
		errors.add(new CoreError("title", "Must not be empty!"));
		errors.add(new CoreError("author", "Must not be empty!"));
		Mockito.when(validator.validate(request)).thenReturn(errors);

		SearchBooksResponse response = service.execute(request);
		assertTrue(response.hasErrors());
		assertEquals(response.getErrors().size(), 2);

		Mockito.verify(validator).validate(request);
		Mockito.verify(validator).validate(any());
		Mockito.verifyNoInteractions(database);
	}

	@Test
	public void shouldSearchByTitle() {
		SearchBooksRequest request = new SearchBooksRequest("Title", null);
		Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

		List<Book> books = new ArrayList<>();
		books.add(new Book("Title", "Author"));
		Mockito.when(database.findByTitle("Title")).thenReturn(books);

		SearchBooksResponse response = service.execute(request);
		assertFalse(response.hasErrors());
		assertEquals(response.getBooks().size(), 1);
		assertEquals(response.getBooks().get(0).getTitle(), "Title");
		assertEquals(response.getBooks().get(0).getAuthor(), "Author");
	}

	@Test
	public void shouldSearchByAuthor() {
		SearchBooksRequest request = new SearchBooksRequest(null, "Author");
		Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

		List<Book> books = new ArrayList<>();
		books.add(new Book("Title", "Author"));
		Mockito.when(database.findByAuthor("Author")).thenReturn(books);

		SearchBooksResponse response = service.execute(request);
		assertFalse(response.hasErrors());
		assertEquals(response.getBooks().size(), 1);
		assertEquals(response.getBooks().get(0).getTitle(), "Title");
		assertEquals(response.getBooks().get(0).getAuthor(), "Author");
	}

	@Test
	public void shouldSearchByTitleAndAuthor() {
		SearchBooksRequest request = new SearchBooksRequest("Title", "Author");
		Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

		List<Book> books = new ArrayList<>();
		books.add(new Book("Title", "Author"));
		Mockito.when(database.findByTitleAndAuthor("Title", "Author")).thenReturn(books);

		SearchBooksResponse response = service.execute(request);
		assertFalse(response.hasErrors());
		assertEquals(response.getBooks().size(), 1);
		assertEquals(response.getBooks().get(0).getTitle(), "Title");
		assertEquals(response.getBooks().get(0).getAuthor(), "Author");
	}

	@Test
	public void shouldSearchByTitleWithOrderingAscending() {
		Ordering ordering = new Ordering("author", "ASCENDING");
		SearchBooksRequest request = new SearchBooksRequest("Title", null, ordering);
		Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

		List<Book> books = new ArrayList<>();
		books.add(new Book("Title", "Author2"));
		books.add(new Book("Title", "Author1"));
		Mockito.when(database.findByTitle("Title")).thenReturn(books);

		SearchBooksResponse response = service.execute(request);
		assertFalse(response.hasErrors());
		assertEquals(response.getBooks().size(), 2);
		assertEquals(response.getBooks().get(0).getAuthor(), "Author1");
		assertEquals(response.getBooks().get(1).getAuthor(), "Author2");
	}

	@Test
	public void shouldSearchByTitleWithOrderingDescending() {
		Ordering ordering = new Ordering("author", "DESCENDING");
		SearchBooksRequest request = new SearchBooksRequest("Title", null, ordering);
		Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

		List<Book> books = new ArrayList<>();
		books.add(new Book("Title", "Author1"));
		books.add(new Book("Title", "Author2"));
		Mockito.when(database.findByTitle("Title")).thenReturn(books);

		SearchBooksResponse response = service.execute(request);
		assertFalse(response.hasErrors());
		assertEquals(response.getBooks().size(), 2);
		assertEquals(response.getBooks().get(0).getAuthor(), "Author2");
		assertEquals(response.getBooks().get(1).getAuthor(), "Author1");
	}

	@Test
	public void shouldSearchByTitleWithPagingFirstPage() {
		Paging paging = new Paging(1, 1);
		SearchBooksRequest request = new SearchBooksRequest("Title", null, null, paging);
		Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

		List<Book> books = new ArrayList<>();
		books.add(new Book("Title", "Author1"));
		books.add(new Book("Title", "Author2"));
		Mockito.when(database.findByTitle("Title")).thenReturn(books);

		SearchBooksResponse response = service.execute(request);
		assertFalse(response.hasErrors());
		assertEquals(response.getBooks().size(), 1);
		assertEquals(response.getBooks().get(0).getTitle(), "Title");
		assertEquals(response.getBooks().get(0).getAuthor(), "Author1");
	}

	@Test
	public void shouldSearchByTitleWithPagingSecondPage() {
		Paging paging = new Paging(2, 1);
		SearchBooksRequest request = new SearchBooksRequest("Title", null, null, paging);
		Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

		List<Book> books = new ArrayList<>();
		books.add(new Book("Title", "Author1"));
		books.add(new Book("Title", "Author2"));
		Mockito.when(database.findByTitle("Title")).thenReturn(books);

		SearchBooksResponse response = service.execute(request);
		assertFalse(response.hasErrors());
		assertEquals(response.getBooks().size(), 1);
		assertEquals(response.getBooks().get(0).getTitle(), "Title");
		assertEquals(response.getBooks().get(0).getAuthor(), "Author2");
	}

}