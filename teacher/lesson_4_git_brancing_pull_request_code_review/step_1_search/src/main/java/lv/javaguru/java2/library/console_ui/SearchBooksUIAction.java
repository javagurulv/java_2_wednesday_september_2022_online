package lv.javaguru.java2.library.console_ui;

import java.util.Scanner;

import lv.javaguru.java2.library.Book;
import lv.javaguru.java2.library.core.requests.SearchBooksRequest;
import lv.javaguru.java2.library.core.responses.SearchBooksResponse;
import lv.javaguru.java2.library.core.services.SearchBooksService;

public class SearchBooksUIAction implements UIAction {

	private SearchBooksService searchBooksService;

	public SearchBooksUIAction(SearchBooksService searchBooksService) {
		this.searchBooksService = searchBooksService;
	}

	@Override
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter book title: ");
		String title = scanner.nextLine();
		System.out.println("Enter book author: ");
		String author = scanner.nextLine();

		SearchBooksRequest request = new SearchBooksRequest(title, author);
		SearchBooksResponse response = searchBooksService.execute(request);

		if (response.hasErrors()) {
			response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
		} else {
			response.getBooks().forEach(Book::toString);
		}
	}

}
