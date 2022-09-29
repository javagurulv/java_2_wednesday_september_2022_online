package lv.javaguru.java2.library.core.services;

import java.util.ArrayList;
import java.util.List;

import lv.javaguru.java2.library.core.database.Database;
import lv.javaguru.java2.library.core.requests.RemoveBookRequest;
import lv.javaguru.java2.library.core.responses.AddBookResponse;
import lv.javaguru.java2.library.core.responses.CoreError;
import lv.javaguru.java2.library.core.responses.RemoveBookResponse;
import lv.javaguru.java2.library.core.services.validators.RemoveBookRequestValidator;
import lv.javaguru.java2.library.dependency_injection.DIComponent;
import lv.javaguru.java2.library.dependency_injection.DIDependency;

@DIComponent
public class RemoveBookService {

	@DIDependency private Database database;
	@DIDependency private RemoveBookRequestValidator validator;

	public RemoveBookResponse execute(RemoveBookRequest request) {
		List<CoreError> errors = validator.validate(request);
		if (!errors.isEmpty()) {
			return new RemoveBookResponse(errors);
		}
		boolean isBookRemoved = database.deleteById(request.getBookId());
		return new RemoveBookResponse(isBookRemoved);
	}

}