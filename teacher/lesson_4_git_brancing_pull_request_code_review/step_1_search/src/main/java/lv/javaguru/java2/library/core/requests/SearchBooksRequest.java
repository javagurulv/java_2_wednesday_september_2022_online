package lv.javaguru.java2.library.core.requests;

public class SearchBooksRequest {

	private String title;
	private String author;

	public SearchBooksRequest(String title, String author) {
		this.title = title;
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public boolean isTitleProvided() {
		return this.title != null && !this.title.isEmpty();
	}

	public boolean isAuthorProvided() {
		return this.author != null && !this.author.isEmpty();
	}

}
