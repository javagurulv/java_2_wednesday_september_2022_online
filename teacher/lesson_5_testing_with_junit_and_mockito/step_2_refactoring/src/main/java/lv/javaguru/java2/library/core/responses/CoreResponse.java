package lv.javaguru.java2.library.core.responses;

import java.util.List;

abstract class CoreResponse {

	private List<CoreError> errors;

	public CoreResponse() { }

	public CoreResponse(List<CoreError> errors) {
		this.errors = errors;
	}

	public List<CoreError> getErrors() {
		return errors;
	}

	public boolean hasErrors() {
		return errors != null && !errors.isEmpty();
	}
}