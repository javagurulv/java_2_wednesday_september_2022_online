package lv.javaguru.java2.library.matchers;

import org.mockito.ArgumentMatcher;

import lv.javaguru.java2.library.core.domain.Book;

public class BookMatcher implements ArgumentMatcher<Book> {
	private String title;
	private String author;

	public BookMatcher(String title, String author) {
		this.title = title;
		this.author = author;
	}

	@Override
	public boolean matches(Book book) {
		return book.getTitle().equals(title)
				&& book.getAuthor().equals(author);
	}
}