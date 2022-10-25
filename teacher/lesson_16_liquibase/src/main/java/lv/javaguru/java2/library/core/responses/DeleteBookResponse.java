package lv.javaguru.java2.library.core.responses;

import java.util.List;

import lv.javaguru.java2.library.core.domain.Book;

public class DeleteBookResponse extends CoreResponse {

	private Book deletedBook;

	public DeleteBookResponse(List<CoreError> errors) {
		super(errors);
	}

	public DeleteBookResponse(Book deletedBook) {
		this.deletedBook = deletedBook;
	}

	public Book getDeletedBook() {
		return deletedBook;
	}

}
