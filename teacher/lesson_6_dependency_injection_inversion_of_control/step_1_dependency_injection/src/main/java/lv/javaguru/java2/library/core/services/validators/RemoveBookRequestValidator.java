package lv.javaguru.java2.library.core.services.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lv.javaguru.java2.library.core.requests.RemoveBookRequest;
import lv.javaguru.java2.library.core.responses.CoreError;
import lv.javaguru.java2.library.dependency_injection.DIComponent;

@DIComponent
public class RemoveBookRequestValidator {

	public List<CoreError> validate(RemoveBookRequest request) {
		List<CoreError> errors = new ArrayList<>();
		validateBookId(request).ifPresent(errors::add);
		return errors;
	}

	private Optional<CoreError> validateBookId(RemoveBookRequest request) {
		return (request.getBookId() == null)
			? Optional.of(new CoreError("bookId", "Must not be empty!"))
			: Optional.empty();
	}

}
