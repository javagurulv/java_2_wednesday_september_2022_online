package lv.javaguru.java2.library.core.services.validators;

import java.util.ArrayList;
import java.util.List;

import lv.javaguru.java2.library.core.requests.AddBookRequest;
import lv.javaguru.java2.library.core.responses.CoreError;

public class AddBookRequestValidatorMock extends AddBookRequestValidator {

	@Override
	public List<CoreError> validate(AddBookRequest request) {
		List<CoreError> errors = new ArrayList<>();

		return errors;
	}

}
