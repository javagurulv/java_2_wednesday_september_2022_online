package lv.javaguru.java2.library.console_ui;

import java.util.Scanner;

import lv.javaguru.java2.library.Book;
import lv.javaguru.java2.library.core.requests.Ordering;
import lv.javaguru.java2.library.core.requests.Paging;
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

		System.out.println("Enter orderBy (title||author): ");
		String orderBy = scanner.nextLine();
		System.out.println("Enter orderDirection (ASCENDING||DESCENDING): ");
		String orderDirection = scanner.nextLine();
		Ordering ordering = new Ordering(orderBy, orderDirection);

		System.out.println("Enter pageNumber: ");
		Integer pageNumber = Integer.parseInt(scanner.nextLine());
		System.out.println("Enter pageSize: ");
		Integer pageSize = Integer.parseInt(scanner.nextLine());
		Paging paging = new Paging(pageNumber, pageSize);

		SearchBooksRequest request = new SearchBooksRequest(title, author, ordering, paging);
		SearchBooksResponse response = searchBooksService.execute(request);

		if (response.hasErrors()) {
			response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
		} else {
			response.getBooks().forEach(Book::toString);
		}
	}

}
