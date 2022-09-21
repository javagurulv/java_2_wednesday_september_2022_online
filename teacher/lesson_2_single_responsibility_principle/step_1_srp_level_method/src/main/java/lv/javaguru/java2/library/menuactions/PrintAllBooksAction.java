package lv.javaguru.java2.library.menuactions;

import java.util.List;

import lv.javaguru.java2.library.Book;

public class PrintAllBooksAction implements Action {

	@Override
	public void execute(List<Book> books) {
		System.out.println("Book list: ");
		for (Book book : books) {
			System.out.println(book);
		}
		System.out.println("Book list end.");
	}

}
