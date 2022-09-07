package lv.javaguru.java2.library.core.services;

import java.util.List;

import lv.javaguru.java2.library.Book;
import lv.javaguru.java2.library.core.database.Database;
import lv.javaguru.java2.library.core.requests.SearchBooksRequest;
import lv.javaguru.java2.library.core.responses.CoreError;
import lv.javaguru.java2.library.core.responses.SearchBooksResponse;

public class SearchBooksService {

	private Database database;
	private SearchBooksRequestValidator validator;

	public SearchBooksService(Database database,
							  SearchBooksRequestValidator validator) {
		this.database = database;
		this.validator = validator;
	}

	public SearchBooksResponse execute(SearchBooksRequest request) {
		List<CoreError> errors = validator.validate(request);
		if (!errors.isEmpty()) {
			return new SearchBooksResponse(null, errors);
		}

		List<Book> books = null;
		if (request.isTitleProvided() && !request.isAuthorProvided()) {
			books = database.findByTitle(request.getTitle());
		}
		if (!request.isTitleProvided() && request.isAuthorProvided()) {
			books = database.findByAuthor(request.getAuthor());
		}
		if (request.isTitleProvided() && request.isAuthorProvided()) {
			books = database.findByTitleAndAuthor(request.getTitle(), request.getAuthor());
		}

		return new SearchBooksResponse(books, null);
	}

}
