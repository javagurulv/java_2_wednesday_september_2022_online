package lv.javaguru.java2.library.console_ui;

import lv.javaguru.java2.library.database.Database;

public class GetAllBooksUIAction implements UIAction {

	private Database database;

	public GetAllBooksUIAction(Database database) {
		this.database = database;
	}

	@Override
	public void execute() {
		System.out.println("Book list: ");
		database.getAllBooks().forEach(System.out::println);
		System.out.println("Book list end.");
	}
}