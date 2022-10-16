package lv.javaguru.java2.library.console_ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lv.javaguru.java2.library.core.requests.GetAllBooksRequest;
import lv.javaguru.java2.library.core.responses.GetAllBooksResponse;
import lv.javaguru.java2.library.core.services.GetAllBooksService;

@Component
public class GetAllBooksUIAction implements UIAction {

	@Autowired private GetAllBooksService getAllBooksService;

	@Override
	public void execute() {
		System.out.println("Book list: ");
		GetAllBooksRequest request = new GetAllBooksRequest();
		GetAllBooksResponse response = getAllBooksService.execute(request);
		response.getBooks().forEach(System.out::println);
		System.out.println("Book list end.");
	}
}