package lv.javaguru.java2.library.services;

import java.util.List;

import lv.javaguru.java2.library.Book;
import lv.javaguru.java2.library.database.Database;

public class GetAllBooksService {

	private Database database;

	public GetAllBooksService(Database database) {
		this.database = database;
	}

	public List<Book> execute() {
		return database.getAllBooks();
	}

}