package lv.javaguru.java2.library.console_ui;

import java.util.Scanner;

import lv.javaguru.java2.library.services.RemoveBookService;

public class RemoveBookUIAction implements UIAction {

	private RemoveBookService removeBookService;

	public RemoveBookUIAction(RemoveBookService removeBookService) {
		this.removeBookService = removeBookService;
	}

	@Override
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter book id to remove: ");
		Long bookId = Long.parseLong(scanner.nextLine());
		removeBookService.execute(bookId);
		System.out.println("Your book was removed from list.");
	}
}