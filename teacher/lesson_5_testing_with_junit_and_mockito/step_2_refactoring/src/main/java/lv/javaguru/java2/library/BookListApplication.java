package lv.javaguru.java2.library;

import java.util.Scanner;

import lv.javaguru.java2.library.console_ui.AddBookUIAction;
import lv.javaguru.java2.library.console_ui.ExitUIAction;
import lv.javaguru.java2.library.console_ui.GetAllBooksUIAction;
import lv.javaguru.java2.library.console_ui.RemoveBookUIAction;
import lv.javaguru.java2.library.console_ui.SearchBooksUIAction;
import lv.javaguru.java2.library.console_ui.UIAction;
import lv.javaguru.java2.library.core.database.Database;
import lv.javaguru.java2.library.core.database.InMemoryDatabaseImpl;
import lv.javaguru.java2.library.core.services.AddBookService;
import lv.javaguru.java2.library.core.services.validators.AddBookRequestValidator;
import lv.javaguru.java2.library.core.services.GetAllBooksService;
import lv.javaguru.java2.library.core.services.RemoveBookService;
import lv.javaguru.java2.library.core.services.SearchBooksService;
import lv.javaguru.java2.library.core.services.validators.OrderingValidator;
import lv.javaguru.java2.library.core.services.validators.PagingValidator;
import lv.javaguru.java2.library.core.services.validators.RemoveBookRequestValidator;
import lv.javaguru.java2.library.core.services.validators.SearchBooksRequestFieldValidator;
import lv.javaguru.java2.library.core.services.validators.SearchBooksRequestValidator;

public class BookListApplication {

	private static Database database = new InMemoryDatabaseImpl();

	private static AddBookRequestValidator addBookRequestValidator = new AddBookRequestValidator();
	private static RemoveBookRequestValidator removeBookRequestValidator = new RemoveBookRequestValidator();

	private static SearchBooksRequestFieldValidator searchBooksRequestFieldValidator = new SearchBooksRequestFieldValidator();
	private static OrderingValidator orderingValidator = new OrderingValidator();
	private static PagingValidator pagingValidator = new PagingValidator();
	private static SearchBooksRequestValidator searchBooksRequestValidator = new SearchBooksRequestValidator(
			searchBooksRequestFieldValidator, orderingValidator, pagingValidator
	);

	private static AddBookService addBookService = new AddBookService(database, addBookRequestValidator);
	private static RemoveBookService removeBookService = new RemoveBookService(database, removeBookRequestValidator);
	private static GetAllBooksService getAllBooksService = new GetAllBooksService(database);
	private static SearchBooksService searchBooksService = new SearchBooksService(database, searchBooksRequestValidator);

	private static UIAction addBookUIAction = new AddBookUIAction(addBookService);
	private static UIAction removeBookUIAction = new RemoveBookUIAction(removeBookService);
	private static UIAction getAllBooksUIAction = new GetAllBooksUIAction(getAllBooksService);
	private static UIAction exitUIAction = new ExitUIAction();
	private static UIAction searchBooksUIAction = new SearchBooksUIAction(searchBooksService);

	public static void main(String[] args) {
		while (true) {
			printProgramMenu();
			int menuNumber = getMenuNumberFromUser();
			executeSelectedMenuItem(menuNumber);
		}
	}

	private static void printProgramMenu() {
		System.out.println();
		System.out.println("Program menu:");
		System.out.println("1. Add book to list");
		System.out.println("2. Delete book from list");
		System.out.println("3. Show all books in the list");
		System.out.println("4. Search books");
		System.out.println("5. Exit");
		System.out.println();
	}

	private static int getMenuNumberFromUser() {
		System.out.println("Enter menu item number to execute:");
		Scanner scanner = new Scanner(System.in);
		return Integer.parseInt(scanner.nextLine());
	}

	private static void executeSelectedMenuItem(int selectedMenu) {
		switch (selectedMenu) {
			case 1: {
				addBookUIAction.execute();
				break;
			}
			case 2: {
				removeBookUIAction.execute();
				break;
			}
			case 3: {
				getAllBooksUIAction.execute();
				break;
			}
			case 4: {
				searchBooksUIAction.execute();
				break;
			}
			case 5: {
				exitUIAction.execute();
				break;
			}
		}
	}

}