package lv.javaguru.java2.library.core.responses;

import java.util.List;

import lv.javaguru.java2.library.Book;

public class GetAllBooksResponse extends CoreResponse {

	private List<Book> books;

	public GetAllBooksResponse(List<Book> books) {
		this.books = books;
	}

	public List<Book> getBooks() {
		return books;
	}
}
