package lv.javaguru.java2.library.core.services;

import lv.javaguru.java2.library.Book;
import lv.javaguru.java2.library.core.database.Database;
import lv.javaguru.java2.library.core.requests.AddBookRequest;
import lv.javaguru.java2.library.core.responses.AddBookResponse;

public class AddBookService {

	private Database database;

	public AddBookService(Database database) {
		this.database = database;
	}

	public AddBookResponse execute(AddBookRequest request) {
		Book book = new Book(request.getTitle(), request.getAuthor());
		database.save(book);
		return new AddBookResponse(book);
	}

}
