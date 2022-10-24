package lv.javaguru.java2.library.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lv.javaguru.java2.library.core.database.BookRepository;
import lv.javaguru.java2.library.core.requests.GetBookRequest;
import lv.javaguru.java2.library.core.responses.CoreError;
import lv.javaguru.java2.library.core.responses.GetBookResponse;
import lv.javaguru.java2.library.core.services.validators.GetBookValidator;

@Component
@Transactional
public class GetBookService {

	@Autowired private BookRepository bookRepository;
	@Autowired private GetBookValidator validator;

	public GetBookResponse execute(GetBookRequest request) {
		List<CoreError> errors = validator.validate(request);
		if (!errors.isEmpty()) {
			return new GetBookResponse(errors);
		}
		return bookRepository.getById(request.getId())
				.map(GetBookResponse::new)
				.orElseGet(() -> {
					errors.add(new CoreError("id", "Not found!"));
					return new GetBookResponse(errors);
				});
	}

}