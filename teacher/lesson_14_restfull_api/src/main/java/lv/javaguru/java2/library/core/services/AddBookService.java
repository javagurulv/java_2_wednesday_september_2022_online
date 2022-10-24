package lv.javaguru.java2.library.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lv.javaguru.java2.library.core.domain.Book;
import lv.javaguru.java2.library.core.database.BookRepository;
import lv.javaguru.java2.library.core.requests.AddBookRequest;
import lv.javaguru.java2.library.core.responses.AddBookResponse;
import lv.javaguru.java2.library.core.responses.CoreError;
import lv.javaguru.java2.library.core.services.validators.AddBookRequestValidator;

@Component
@Transactional
public class AddBookService {

	@Autowired private BookRepository bookRepository;
	@Autowired private AddBookRequestValidator validator;

	public AddBookResponse execute(AddBookRequest request) {
		List<CoreError> errors = validator.validate(request);
		if (!errors.isEmpty()) {
			return new AddBookResponse(errors);
		}

		Book book = new Book(request.getTitle(), request.getAuthor());
		bookRepository.save(book);

		return new AddBookResponse(book);
	}

}
