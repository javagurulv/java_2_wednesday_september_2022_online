package lv.javaguru.java2.library.core.services;

import java.util.List;

import lv.javaguru.java2.library.Book;
import lv.javaguru.java2.library.core.database.Database;
import lv.javaguru.java2.library.core.requests.AddBookRequest;
import lv.javaguru.java2.library.core.responses.AddBookResponse;
import lv.javaguru.java2.library.core.responses.CoreError;

public class AddBookService {

	private Database database;
	private AddBookRequestValidator validator;

	public AddBookService(Database database,
						  AddBookRequestValidator validator) {
		this.database = database;
		this.validator = validator;
	}

	public AddBookResponse execute(AddBookRequest request) {
		List<CoreError> errors = validator.validate(request);
		if (!errors.isEmpty()) {
			return new AddBookResponse(errors);
		}

		Book book = new Book(request.getTitle(), request.getAuthor());
		database.save(book);

		return new AddBookResponse(book);
	}

}
