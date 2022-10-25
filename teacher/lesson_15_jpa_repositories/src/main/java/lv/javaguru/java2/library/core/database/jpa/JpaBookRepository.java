package lv.javaguru.java2.library.core.database.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lv.javaguru.java2.library.core.domain.Book;


@Repository
public interface JpaBookRepository extends JpaRepository<Book, Long> {

	List<Book> findByTitle(String title);

	List<Book> findByAuthor(String author);

	List<Book> findByTitleAndAuthor(String title, String author);

}
