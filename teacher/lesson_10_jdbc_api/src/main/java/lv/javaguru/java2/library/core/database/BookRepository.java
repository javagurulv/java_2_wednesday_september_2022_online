package lv.javaguru.java2.library.core.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import lv.javaguru.java2.library.core.domain.Book;

@Component
public class BookRepository {

	@Autowired private JdbcTemplate jdbcTemplate;

	public void save(Book book) {
		jdbcTemplate.update(
				"INSERT INTO books (title, author) "
						+ "VALUES (?, ?)",
				book.getTitle(), book.getAuthor()
		);
	}



}
