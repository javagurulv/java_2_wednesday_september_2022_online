package lv.javaguru.java2.library.menuactions;

import java.util.List;

import lv.javaguru.java2.library.Book;

public interface Action {

	void execute(List<Book> books);

}
