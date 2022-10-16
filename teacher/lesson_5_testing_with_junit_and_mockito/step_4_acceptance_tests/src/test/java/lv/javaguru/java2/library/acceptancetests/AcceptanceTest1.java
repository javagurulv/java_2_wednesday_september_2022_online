package lv.javaguru.java2.library.acceptancetests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import lv.javaguru.java2.library.ApplicationContext;
import lv.javaguru.java2.library.core.requests.AddBookRequest;
import lv.javaguru.java2.library.core.requests.GetAllBooksRequest;
import lv.javaguru.java2.library.core.responses.GetAllBooksResponse;
import lv.javaguru.java2.library.core.services.AddBookService;
import lv.javaguru.java2.library.core.services.GetAllBooksService;

public class AcceptanceTest1 {

	private ApplicationContext appContext = new ApplicationContext();

	@Test
	public void shouldReturnCorrectBookList() {
		AddBookRequest addBookRequest1 = new AddBookRequest("TitleA", "AuthorA");
		getAddBookService().execute(addBookRequest1);

		AddBookRequest addBookRequest2 = new AddBookRequest("TitleA", "AuthorA");
		getAddBookService().execute(addBookRequest2);

		GetAllBooksResponse response = getAllBooksService().execute(new GetAllBooksRequest());
		assertEquals(response.getBooks().size(), 2);
	}

	private AddBookService getAddBookService() {
		return appContext.getBean(AddBookService.class);
	}

	private GetAllBooksService getAllBooksService() {
		return appContext.getBean(GetAllBooksService.class);
	}

}
