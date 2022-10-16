package lv.javaguru.java2.library.menuactions;

import java.util.List;

import lv.javaguru.java2.library.Book;

public class ExitProgramAction implements Action {

	@Override
	public void execute(List<Book> books) {
		System.out.println("Good by!");
		System.exit(0);
	}

}
