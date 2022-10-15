package lv.javaguru.java2.library.acceptancetests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lv.javaguru.java2.library.DatabaseCleaner;
import lv.javaguru.java2.library.config.BookListConfiguration;
import lv.javaguru.java2.library.core.requests.AddBookRequest;
import lv.javaguru.java2.library.core.requests.Ordering;
import lv.javaguru.java2.library.core.requests.Paging;
import lv.javaguru.java2.library.core.requests.SearchBooksRequest;
import lv.javaguru.java2.library.core.responses.SearchBooksResponse;
import lv.javaguru.java2.library.core.services.AddBookService;
import lv.javaguru.java2.library.core.services.SearchBooksService;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BookListConfiguration.class})
@Sql({"/schema.sql"})
public class AcceptanceTest2 {

	@Autowired private AddBookService addBookService;
	@Autowired private SearchBooksService searchBooksService;
	@Autowired private DatabaseCleaner databaseCleaner;

	@Before
	public void setup() {
		databaseCleaner.clean();
	}

	@Test
	public void searchBooks() {
		AddBookRequest request1 = new AddBookRequest("Title", "Author1");
		addBookService.execute(request1);

		AddBookRequest request2 = new AddBookRequest("Title", "Author2");
		addBookService.execute(request2);

		SearchBooksRequest request3 = new SearchBooksRequest("Title", null);
		SearchBooksResponse response = searchBooksService.execute(request3);

		assertEquals(response.getBooks().size(), 2);
		assertEquals(response.getBooks().get(0).getTitle(), "Title");
		assertEquals(response.getBooks().get(0).getAuthor(), "Author1");
		assertEquals(response.getBooks().get(1).getTitle(), "Title");
		assertEquals(response.getBooks().get(1).getAuthor(), "Author2");
	}

	@Test
	public void searchBooksOrderingDescending() {
		AddBookRequest request1 = new AddBookRequest("Title", "Author1");
		addBookService.execute(request1);

		AddBookRequest request2 = new AddBookRequest("Title", "Author2");
		addBookService.execute(request2);

		Ordering ordering = new Ordering("author", "DESCENDING");
		SearchBooksRequest request3 = new SearchBooksRequest("Title", null, ordering);
		SearchBooksResponse response = searchBooksService.execute(request3);

		assertEquals(response.getBooks().size(), 2);
		assertEquals(response.getBooks().get(0).getTitle(), "Title");
		assertEquals(response.getBooks().get(0).getAuthor(), "Author2");
		assertEquals(response.getBooks().get(1).getTitle(), "Title");
		assertEquals(response.getBooks().get(1).getAuthor(), "Author1");
	}

	@Test
	public void searchBooksOrderingAscending() {
		AddBookRequest request1 = new AddBookRequest("Title", "Author1");
		addBookService.execute(request1);

		AddBookRequest request2 = new AddBookRequest("Title", "Author2");
		addBookService.execute(request2);

		Ordering ordering = new Ordering("author", "ASCENDING");
		SearchBooksRequest request3 = new SearchBooksRequest("Title", null, ordering);
		SearchBooksResponse response = searchBooksService.execute(request3);

		assertEquals(response.getBooks().size(), 2);
		assertEquals(response.getBooks().get(0).getTitle(), "Title");
		assertEquals(response.getBooks().get(0).getAuthor(), "Author1");
		assertEquals(response.getBooks().get(1).getTitle(), "Title");
		assertEquals(response.getBooks().get(1).getAuthor(), "Author2");
	}

	@Test
	public void searchBooksOrderingPaging() {
		AddBookRequest request1 = new AddBookRequest("Title", "Author1");
		addBookService.execute(request1);

		AddBookRequest request2 = new AddBookRequest("Title", "Author2");
		addBookService.execute(request2);

		Ordering ordering = new Ordering("author", "ASCENDING");
		Paging paging = new Paging(1, 1);
		SearchBooksRequest request3 = new SearchBooksRequest("Title", null, ordering, paging);
		SearchBooksResponse response = searchBooksService.execute(request3);

		assertEquals(response.getBooks().size(), 1);
		assertEquals(response.getBooks().get(0).getTitle(), "Title");
		assertEquals(response.getBooks().get(0).getAuthor(), "Author1");
	}

}
