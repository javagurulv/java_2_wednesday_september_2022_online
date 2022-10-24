package lv.javaguru.java2.library.core.responses;

import java.util.List;

import lv.javaguru.java2.library.core.domain.Book;

public class UpdateBookResponse extends CoreResponse {

	private Book updatedBook;

	public UpdateBookResponse(List<CoreError> errors) {
		super(errors);
	}

	public UpdateBookResponse(Book updatedBook) {
		this.updatedBook = updatedBook;
	}

	public Book getUpdatedBook() {
		return updatedBook;
	}

}