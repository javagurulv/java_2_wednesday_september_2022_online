package lv.javaguru.java2.library.console_ui;

import lv.javaguru.java2.library.core.requests.GetAllBooksRequest;
import lv.javaguru.java2.library.core.responses.GetAllBooksResponse;
import lv.javaguru.java2.library.core.services.GetAllBooksService;
import lv.javaguru.java2.library.dependency_injection.DIComponent;
import lv.javaguru.java2.library.dependency_injection.DIDependency;

@DIComponent
public class GetAllBooksUIAction implements UIAction {

	@DIDependency private GetAllBooksService getAllBooksService;

	@Override
	public void execute() {
		System.out.println("Book list: ");
		GetAllBooksRequest request = new GetAllBooksRequest();
		GetAllBooksResponse response = getAllBooksService.execute(request);
		response.getBooks().forEach(System.out::println);
		System.out.println("Book list end.");
	}
}