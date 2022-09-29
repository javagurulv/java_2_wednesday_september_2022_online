package lv.javaguru.java2.library.core.services.validators;

import java.util.ArrayList;
import java.util.List;

import lv.javaguru.java2.library.core.requests.SearchBooksRequest;
import lv.javaguru.java2.library.core.responses.CoreError;

public class SearchBooksRequestFieldValidator {

	public List<CoreError> validate(SearchBooksRequest request) {
		List<CoreError> errors = new ArrayList<>();
		if (isEmpty(request.getTitle()) && isEmpty(request.getAuthor())) {
			errors.add(new CoreError("title", "Must not be empty!"));
			errors.add(new CoreError("author", "Must not be empty!"));
		}
		return errors;
	}

	private boolean isEmpty(String str) {
		return str == null || str.isEmpty();
	}

}