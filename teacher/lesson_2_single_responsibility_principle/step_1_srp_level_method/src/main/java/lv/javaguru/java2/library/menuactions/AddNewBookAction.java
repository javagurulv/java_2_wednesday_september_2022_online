package lv.javaguru.java2.library.menuactions;

import java.util.List;
import java.util.Scanner;

import lv.javaguru.java2.library.Book;

public class AddNewBookAction implements Action {

	@Override
	public void execute(List<Book> books) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter book title: ");
		String bookTitle = scanner.nextLine();
		System.out.println("Enter book author: ");
		String bookAuthor = scanner.nextLine();

		Book book = new Book(bookTitle, bookAuthor);
		books.add(book);

		System.out.println("Your book was added to list.");
	}

}
