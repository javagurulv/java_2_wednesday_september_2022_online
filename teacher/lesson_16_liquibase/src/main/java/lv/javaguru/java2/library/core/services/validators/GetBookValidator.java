package lv.javaguru.java2.library.core.services.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import lv.javaguru.java2.library.core.requests.GetBookRequest;
import lv.javaguru.java2.library.core.responses.CoreError;

@Component
public class GetBookValidator {

	public List<CoreError> validate(GetBookRequest request) {
		List<CoreError> errors = new ArrayList<>();
		validateId(request).ifPresent(errors::add);
		return errors;
	}

	private Optional<CoreError> validateId(GetBookRequest request) {
		return (request.getId() == null)
				? Optional.of(new CoreError("id", "Must not be empty!"))
				: Optional.empty();
	}

}