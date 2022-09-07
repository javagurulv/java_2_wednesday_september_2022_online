package lv.javaguru.java2.library.core.responses;

import lv.javaguru.java2.library.Book;

public class AddBookResponse {

	private Book newBook;

	public AddBookResponse(Book newBook) {
		this.newBook = newBook;
	}

	public Book getNewBook() {
		return newBook;
	}
}