package lv.javaguru.java2.library.database;

import java.util.List;

import lv.javaguru.java2.library.Book;

public interface Database {

	void save(Book book);

	void deleteById(Long id);

	List<Book> getAllBooks();

}
