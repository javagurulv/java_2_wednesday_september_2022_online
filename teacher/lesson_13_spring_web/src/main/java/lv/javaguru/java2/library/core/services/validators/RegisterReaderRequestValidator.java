package lv.javaguru.java2.library.core.services.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import lv.javaguru.java2.library.core.requests.RegisterReaderRequest;
import lv.javaguru.java2.library.core.responses.CoreError;

@Component
public class RegisterReaderRequestValidator {

	public List<CoreError> validate(RegisterReaderRequest request) {
		List<CoreError> errors = new ArrayList<>();
		validateFirstName(request).ifPresent(errors::add);
		validateLastName(request).ifPresent(errors::add);
		return errors;
	}

	private Optional<CoreError> validateFirstName(RegisterReaderRequest request) {
		return (request.getFirstName() == null || request.getFirstName().isEmpty())
				? Optional.of(new CoreError("firstName", "Must not be empty!"))
				: Optional.empty();
	}

	private Optional<CoreError> validateLastName(RegisterReaderRequest request) {
		return (request.getLastName() == null || request.getLastName().isEmpty())
				? Optional.of(new CoreError("lastName", "Must not be empty!"))
				: Optional.empty();
	}

}