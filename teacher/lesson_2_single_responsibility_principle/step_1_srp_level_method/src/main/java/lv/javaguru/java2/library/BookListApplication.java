package lv.javaguru.java2.library;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookListApplication {

	public static void main(String[] args) {
		List<Book> books = new ArrayList<>();
		while (true) {
			printProgramMenu();
			int menuNumber = getMenuNumberFromUser();
			executeSelectedMenuItem(books, menuNumber);
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

	private static void executeSelectedMenuItem(List<Book> books, int selectedMenu) {
		switch (selectedMenu) {
			case 1: {
				addNewBookAction(books);
				break;
			}
			case 2: {
				removeBookAction(books);
				break;
			}
			case 3: {
				printAllBooksAction(books);
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

	private static void printAllBooksAction(List<Book> books) {
		System.out.println("Book list: ");
		for (Book book : books) {
			System.out.println(book);
		}
		System.out.println("Book list end.");
	}

	private static void removeBookAction(List<Book> books) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter book title: ");
		String bookTitle = scanner.nextLine();
		System.out.println("Enter book author: ");
		String bookAuthor = scanner.nextLine();
		books.remove(new Book(bookTitle, bookAuthor));
		System.out.println("Your book was removed from list.");
	}

	private static void addNewBookAction(List<Book> books) {
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