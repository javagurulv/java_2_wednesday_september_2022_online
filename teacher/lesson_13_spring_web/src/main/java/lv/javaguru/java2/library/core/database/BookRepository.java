package lv.javaguru.java2.library.core.database;

import java.util.List;

import lv.javaguru.java2.library.core.domain.Book;

public interface BookRepository {

	void save(Book book);

	boolean deleteById(Long id);

	List<Book> getAllBooks();

	List<Book> findByTitle(String title);

	List<Book> findByAuthor(String author);

	List<Book> findByTitleAndAuthor(String title, String author);

}
