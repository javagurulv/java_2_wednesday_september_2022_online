package lv.javaguru.java2.library.core.acceptancetests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lv.javaguru.java2.library.config.SpringCoreConfiguration;
import lv.javaguru.java2.library.core.DatabaseCleaner;
import lv.javaguru.java2.library.core.requests.AddBookRequest;
import lv.javaguru.java2.library.core.requests.GetAllBooksRequest;
import lv.javaguru.java2.library.core.responses.GetAllBooksResponse;
import lv.javaguru.java2.library.core.services.AddBookService;
import lv.javaguru.java2.library.core.services.GetAllBooksService;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringCoreConfiguration.class})
public class AcceptanceTest1 {

	@Autowired private AddBookService addBookService;
	@Autowired private GetAllBooksService getAllBooksService;
	@Autowired private DatabaseCleaner databaseCleaner;

	@Before
	public void setup() {
		databaseCleaner.clean();
	}

	@Test
	public void shouldReturnCorrectBookList() {
		AddBookRequest addBookRequest1 = new AddBookRequest("TitleA", "AuthorA");
		addBookService.execute(addBookRequest1);

		AddBookRequest addBookRequest2 = new AddBookRequest("TitleA", "AuthorA");
		addBookService.execute(addBookRequest2);

		GetAllBooksResponse response = getAllBooksService.execute(new GetAllBooksRequest());
		assertEquals(response.getBooks().size(), 2);
	}

}
