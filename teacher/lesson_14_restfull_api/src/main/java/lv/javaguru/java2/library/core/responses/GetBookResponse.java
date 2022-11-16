package lv.javaguru.java2.library.core.responses;

import java.util.List;

import lv.javaguru.java2.library.core.domain.Book;

public class GetBookResponse extends CoreResponse {

	private Book book;

	public GetBookResponse(List<CoreError> errors) {
		super(errors);
	}

	public GetBookResponse(Book book) {
		this.book = book;
	}

	public Book getBook() {
		return book;
	}

}
