package lv.javaguru.java2.library.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lv.javaguru.java2.library.core.database.BookRepository;
import lv.javaguru.java2.library.core.database.jpa.JpaBookRepository;
import lv.javaguru.java2.library.core.requests.UpdateBookRequest;
import lv.javaguru.java2.library.core.responses.CoreError;
import lv.javaguru.java2.library.core.responses.UpdateBookResponse;
import lv.javaguru.java2.library.core.services.validators.UpdateBookRequestValidator;

@Component
@Transactional
public class UpdateBookService {

	@Autowired private JpaBookRepository bookRepository;
	@Autowired private UpdateBookRequestValidator validator;

	public UpdateBookResponse execute(UpdateBookRequest request) {
		List<CoreError> errors = validator.validate(request);
		if (!errors.isEmpty()) {
			return new UpdateBookResponse(errors);
		}

		return bookRepository.findById(request.getId())
				.map(book -> {
					book.setTitle(request.getNewTitle());
					book.setAuthor(request.getNewAuthor());
					return new UpdateBookResponse(book);
				})
				.orElseGet(() -> {
					errors.add(new CoreError("id", "Not found!"));
					return new UpdateBookResponse(errors);
				});
	}

}