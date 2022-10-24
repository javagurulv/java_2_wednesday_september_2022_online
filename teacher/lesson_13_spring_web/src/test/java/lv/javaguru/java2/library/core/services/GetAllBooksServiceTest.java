package lv.javaguru.java2.library.core.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import lv.javaguru.java2.library.core.domain.Book;
import lv.javaguru.java2.library.core.database.BookRepository;
import lv.javaguru.java2.library.core.requests.GetAllBooksRequest;
import lv.javaguru.java2.library.core.responses.GetAllBooksResponse;

@RunWith(MockitoJUnitRunner.class)
public class GetAllBooksServiceTest {

	@Mock private BookRepository bookRepository;
	@InjectMocks private GetAllBooksService service;

	@Test
	public void shouldGetBooksFromDb() {
		List<Book> books = new ArrayList<>();
		books.add(new Book("Title", "Author"));
		Mockito.when(bookRepository.getAllBooks()).thenReturn(books);

		GetAllBooksRequest request = new GetAllBooksRequest();
		GetAllBooksResponse response = service.execute(request);
		assertFalse(response.hasErrors());
		assertEquals(response.getBooks().size(), 1);
		assertEquals(response.getBooks().get(0).getTitle(), "Title");
		assertEquals(response.getBooks().get(0).getAuthor(), "Author");
	}

}