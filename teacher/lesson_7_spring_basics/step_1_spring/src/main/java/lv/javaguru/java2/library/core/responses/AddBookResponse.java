package lv.javaguru.java2.library.core.responses;

import java.util.List;

import lv.javaguru.java2.library.core.domain.Book;

public class AddBookResponse extends CoreResponse {

	private Book newBook;

	public AddBookResponse(List<CoreError> errors) {
		super(errors);
	}

	public AddBookResponse(Book newBook) {
		this.newBook = newBook;
	}

	public Book getNewBook() {
		return newBook;
	}
}