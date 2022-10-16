package lv.javaguru.java2.library.core.services;

import java.util.ArrayList;
import java.util.List;

import lv.javaguru.java2.library.core.requests.database.Database;
import lv.javaguru.java2.library.core.requests.RemoveBookRequest;
import lv.javaguru.java2.library.core.responses.CoreError;
import lv.javaguru.java2.library.core.responses.RemoveBookResponse;

public class RemoveBookService {

	private Database database;

	public RemoveBookService(Database database) {
		this.database = database;
	}

	public RemoveBookResponse execute(RemoveBookRequest request) {
		if (request.getBookIdToRemove() == null) {
			CoreError error = new CoreError("id", "Must not be empty!");
			List<CoreError> errors = new ArrayList<>();
			errors.add(error);
			return new RemoveBookResponse(errors);
		}
		boolean isBookRemoved = database.deleteById(request.getBookIdToRemove());
		return new RemoveBookResponse(isBookRemoved);
	}

}