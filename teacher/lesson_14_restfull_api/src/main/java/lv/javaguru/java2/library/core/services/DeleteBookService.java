package lv.javaguru.java2.library.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lv.javaguru.java2.library.core.database.BookRepository;
import lv.javaguru.java2.library.core.requests.DeleteBookRequest;
import lv.javaguru.java2.library.core.responses.CoreError;
import lv.javaguru.java2.library.core.responses.DeleteBookResponse;
import lv.javaguru.java2.library.core.services.validators.DeleteBookValidator;

@Component
@Transactional
public class DeleteBookService {

	@Autowired private BookRepository bookRepository;
	@Autowired private DeleteBookValidator validator;

	public DeleteBookResponse execute(DeleteBookRequest request) {
		List<CoreError> errors = validator.validate(request);
		if (!errors.isEmpty()) {
			return new DeleteBookResponse(errors);
		}
		return bookRepository.getById(request.getId())
				.map(book -> {
					bookRepository.deleteById(request.getId());
					return new DeleteBookResponse(book);
				})
				.orElseGet(() -> {
					errors.add(new CoreError("id", "Not found!"));
					return new DeleteBookResponse(errors);
				});
	}

}