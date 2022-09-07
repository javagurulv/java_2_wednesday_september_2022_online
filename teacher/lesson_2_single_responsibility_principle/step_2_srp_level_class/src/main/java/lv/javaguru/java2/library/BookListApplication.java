package lv.javaguru.java2.library;

import java.util.Scanner;

public class BookListApplication {

	public static void main(String[] args) {
		Database database = new InMemoryDatabaseImpl();
		while (true) {
			printProgramMenu();
			int menuNumber = getMenuNumberFromUser();
			executeSelectedMenuItem(database, menuNumber);
		}
	}

	private static void printProgramMenu() {
		System.out.println();
		System.out.println("Program menu:");
		System.out.println("1. Add book to list");
		System.out.println("2. Delete book from list");
		System.out.println("3. Show all books in the list");
		System.out.println("4. Exit");
		System.out.println();
	}

	private static int getMenuNumberFromUser() {
		System.out.println("Enter menu item number to execute:");
		Scanner scanner = new Scanner(System.in);
		return Integer.parseInt(scanner.nextLine());
	}

	private static void executeSelectedMenuItem(Database database, int selectedMenu) {
		switch (selectedMenu) {
			case 1: {
				addNewBookAction(database);
				break;
			}
			case 2: {
				removeBookAction(database);
				break;
			}
			case 3: {
				printAllBooksAction(database);
				break;
			}
			case 4: {
				exitProgramAction();
				break;
			}
		}
	}

	private static void exitProgramAction() {
		System.out.println("Good by!");
		System.exit(0);
	}

	private static void printAllBooksAction(Database database) {
		System.out.println("Book list: ");
		database.getAllBooks().forEach(System.out::println);
		System.out.println("Book list end.");
	}

	private static void removeBookAction(Database database) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter book id to remove: ");
		Long bookId = Long.parseLong(scanner.nextLine());
		database.deleteById(bookId);
		System.out.println("Your book was removed from list.");
	}

	private static void addNewBookAction(Database database) {
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