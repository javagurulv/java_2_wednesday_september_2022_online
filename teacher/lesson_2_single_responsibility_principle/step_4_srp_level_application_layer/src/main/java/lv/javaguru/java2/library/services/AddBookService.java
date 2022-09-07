package lv.javaguru.java2.library.services;

import lv.javaguru.java2.library.Book;
import lv.javaguru.java2.library.database.Database;

public class AddBookService {

	private Database database;

	public AddBookService(Database database) {
		this.database = database;
	}

	public void execute(String bookTitle, String bookAuthor) {
		Book book = new Book(bookTitle, bookAuthor);
		database.save(book);
	}

}
