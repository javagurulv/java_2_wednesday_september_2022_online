package lv.javaguru.java2.library.console_ui;

import java.util.Scanner;

import lv.javaguru.java2.library.Book;
import lv.javaguru.java2.library.database.Database;

public class AddBookUIAction implements UIAction {

	private Database database;

	public AddBookUIAction(Database database) {
		this.database = database;
	}

	@Override
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter book title: ");
		String bookTitle = scanner.nextLine();
		System.out.println("Enter book author: ");
		String bookAuthor = scanner.nextLine();
		Book book = new Book(bookTitle, bookAuthor);
		database.save(book);
		System.out.println("Your book was added to list.");
	}

}