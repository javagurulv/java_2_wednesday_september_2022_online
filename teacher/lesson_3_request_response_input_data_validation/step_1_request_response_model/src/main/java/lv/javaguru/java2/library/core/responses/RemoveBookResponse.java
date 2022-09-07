package lv.javaguru.java2.library.core.responses;

public class RemoveBookResponse {

	private boolean bookRemoved;

	public RemoveBookResponse(boolean bookRemoved) {
		this.bookRemoved = bookRemoved;
	}

	public boolean isBookRemoved() {
		return bookRemoved;
	}
}