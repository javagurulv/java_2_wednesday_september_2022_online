package lv.javaguru.java2.library.core.services.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lv.javaguru.java2.library.core.requests.Paging;
import lv.javaguru.java2.library.core.responses.CoreError;

public class PagingValidator {

	public List<CoreError> validate(Paging paging) {
		List<CoreError> errors = new ArrayList<>();
		validatePageNumber(paging).ifPresent(errors::add);
		validatePageSize(paging).ifPresent(errors::add);
		validateMandatoryPageNumber(paging).ifPresent(errors::add);
		validateMandatoryPageSize(paging).ifPresent(errors::add);
		return errors;
	}

	private Optional<CoreError> validatePageNumber(Paging paging) {
		return (paging.getPageNumber() != null
				&& paging.getPageNumber() <= 0)
				? Optional.of(new CoreError("pageNumber", "Must be greater then 0!"))
				: Optional.empty();
	}

	private Optional<CoreError> validatePageSize(Paging paging) {
		return (paging.getPageSize() != null
				&& paging.getPageSize() <= 0)
				? Optional.of(new CoreError("pageSize", "Must be greater then 0!"))
				: Optional.empty();
	}

	private Optional<CoreError> validateMandatoryPageNumber(Paging paging) {
		return (paging.getPageNumber() == null && paging.getPageSize() != null)
				? Optional.of(new CoreError("pageNumber", "Must not be empty!"))
				: Optional.empty();
	}

	private Optional<CoreError> validateMandatoryPageSize(Paging paging) {
		return (paging.getPageSize() == null && paging.getPageNumber() != null)
				? Optional.of(new CoreError("pageSize", "Must not be empty!"))
				: Optional.empty();
	}

}