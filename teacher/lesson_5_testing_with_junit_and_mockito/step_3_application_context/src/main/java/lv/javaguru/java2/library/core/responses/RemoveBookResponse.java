package lv.javaguru.java2.library.core.responses;

import java.util.List;

public class RemoveBookResponse extends CoreResponse {

	private boolean bookRemoved;

	public RemoveBookResponse(List<CoreError> errors) {
		super(errors);
	}

	public RemoveBookResponse(boolean bookRemoved) {
		this.bookRemoved = bookRemoved;
	}

	public boolean isBookRemoved() {
		return bookRemoved;
	}
}