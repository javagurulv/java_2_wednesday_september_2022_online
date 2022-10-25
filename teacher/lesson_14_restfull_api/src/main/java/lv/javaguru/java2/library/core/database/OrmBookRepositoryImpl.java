package lv.javaguru.java2.library.core.database;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lv.javaguru.java2.library.core.domain.Book;

@Component
@Transactional
public class OrmBookRepositoryImpl implements BookRepository {

	@Autowired private SessionFactory sessionFactory;

	@Override
	public void save(Book book) {
		sessionFactory.getCurrentSession().save(book);
	}

	@Override
	public Optional<Book> getById(Long id) {
		Book book = sessionFactory.getCurrentSession().get(Book.class, id);
		if (book == null) {
			return Optional.empty();
		} else {
			return Optional.of(book);
		}
	}

	@Override
	public boolean deleteById(Long id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"delete Book where id = :id");
		query.setParameter("id", id);
		int result = query.executeUpdate();
		return result == 1;
	}

	@Override
	public List<Book> getAllBooks() {
		return sessionFactory.getCurrentSession()
				.createQuery("SELECT b FROM Book b", Book.class)
				.getResultList();
	}

	@Override
	public List<Book> findByTitle(String title) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select b FROM Book b where title = :title");
		query.setParameter("title", title);
		return query.getResultList();
	}

	@Override
	public List<Book> findByAuthor(String author) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select b FROM Book b where author = :author");
		query.setParameter("author", author);
		return query.getResultList();
	}

	@Override
	public List<Book> findByTitleAndAuthor(String title, String author) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select b FROM Book b where title = : title AND author = :author");
		query.setParameter("title", title);
		query.setParameter("author", author);
		return query.getResultList();
	}
}
