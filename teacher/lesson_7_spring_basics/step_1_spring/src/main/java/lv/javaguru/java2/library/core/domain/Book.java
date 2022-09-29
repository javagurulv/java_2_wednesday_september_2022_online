package lv.javaguru.java2.library.core.domain;

import java.util.Objects;

public class Book {

	private Long id;
	private String title;
	private String author;

	public Book(String title, String author) {
		this.title = title;
		this.author = author;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Book book = (Book) o;
		return Objects.equals(id, book.id) &&
				Objects.equals(title, book.title) &&
				Objects.equals(author, book.author);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, author);
	}

	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", title='" + title + '\'' +
				", author='" + author + '\'' +
				'}';
	}
}
