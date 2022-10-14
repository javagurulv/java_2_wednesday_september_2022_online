package lv.javaguru.java2.library.core.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import lv.javaguru.java2.library.core.domain.Book;

@Component
class JdbcDatabaseImpl implements Database {

	@Autowired private JdbcTemplate jdbcTemplate;

	@Override
	public void save(Book book) {
		jdbcTemplate.update(
				"INSERT INTO books (title, author) "
						+ "VALUES (?, ?)",
				book.getTitle(), book.getAuthor()
		);
	}

	@Override
	public boolean deleteById(Long id) {
		String sql = "DELETE FROM books WHERE id = ?";
		Object[] args = new Object[] {id};
		return jdbcTemplate.update(sql, args) == 1;
	}

	@Override
	public List<Book> getAllBooks() {
		String sql = "SELECT * FROM books";
		return jdbcTemplate.query(sql, new BookRowMapper());
	}

	@Override
	public List<Book> findByTitle(String title) {
		String sql = "SELECT * FROM books WHERE title = ?";
		Object[] args = new Object[] {title};
		return jdbcTemplate.query(sql, args, new BookRowMapper());
	}

	@Override
	public List<Book> findByAuthor(String author) {
		String sql = "SELECT * FROM books WHERE author = ?";
		Object[] args = new Object[] {author};
		return jdbcTemplate.query(sql, args, new BookRowMapper());
	}

	@Override
	public List<Book> findByTitleAndAuthor(String title, String author) {
		String sql = "SELECT * FROM books WHERE title = ? AND author = ? ";
		Object[] args = new Object[] {title, author};
		return jdbcTemplate.query(sql, args, new BookRowMapper());
	}
}
