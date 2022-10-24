package lv.javaguru.java2.library.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lv.javaguru.java2.library.core.database.BookRepository;
import lv.javaguru.java2.library.core.database.jpa.JpaBookRepository;
import lv.javaguru.java2.library.core.requests.RemoveBookRequest;
import lv.javaguru.java2.library.core.responses.CoreError;
import lv.javaguru.java2.library.core.responses.RemoveBookResponse;
import lv.javaguru.java2.library.core.services.validators.RemoveBookRequestValidator;

@Component
@Transactional
public class RemoveBookService {

	@Autowired private JpaBookRepository bookRepository;
	@Autowired private RemoveBookRequestValidator validator;

	public RemoveBookResponse execute(RemoveBookRequest request) {
		List<CoreError> errors = validator.validate(request);
		if (!errors.isEmpty()) {
			return new RemoveBookResponse(errors);
		}
		bookRepository.deleteById(request.getBookId());
		return new RemoveBookResponse(true);
	}

}