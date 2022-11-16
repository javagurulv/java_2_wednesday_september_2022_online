package lv.javaguru.java2.library.core.services;

import javax.transaction.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lv.javaguru.java2.library.core.domain.Book;
import lv.javaguru.java2.library.core.database.BookRepository;
import lv.javaguru.java2.library.core.requests.GetAllBooksRequest;
import lv.javaguru.java2.library.core.responses.GetAllBooksResponse;

@Component
@Transactional
public class GetAllBooksService {

	@Autowired private BookRepository bookRepository;

	public GetAllBooksResponse execute(GetAllBooksRequest request) {
		List<Book> books = bookRepository.getAllBooks();
		return new GetAllBooksResponse(books);
	}

}