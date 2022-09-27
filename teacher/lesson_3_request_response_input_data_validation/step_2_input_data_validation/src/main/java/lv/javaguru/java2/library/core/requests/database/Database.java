package lv.javaguru.java2.library.core.requests.database;

import java.util.List;

import lv.javaguru.java2.library.Book;

public interface Database {

	void save(Book book);

	boolean deleteById(Long id);

	List<Book> getAllBooks();

}
