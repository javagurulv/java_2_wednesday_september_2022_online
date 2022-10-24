package lv.javaguru.java2.library.core.database;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lv.javaguru.java2.library.core.domain.ReaderBook;

//@Component
//@Transactional
public class ReaderBookRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public void save(ReaderBook readerBook) {
		sessionFactory.getCurrentSession().save(readerBook);
	}

	public ReaderBook getById(Long id) {
		return sessionFactory.getCurrentSession()
				.get(ReaderBook.class, id);
	}


}
