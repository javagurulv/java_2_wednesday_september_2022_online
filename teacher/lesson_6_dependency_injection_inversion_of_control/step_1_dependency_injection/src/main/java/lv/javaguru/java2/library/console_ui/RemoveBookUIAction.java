package lv.javaguru.java2.library.console_ui;

import java.util.Scanner;

import lv.javaguru.java2.library.core.requests.RemoveBookRequest;
import lv.javaguru.java2.library.core.responses.RemoveBookResponse;
import lv.javaguru.java2.library.core.services.RemoveBookService;
import lv.javaguru.java2.library.dependency_injection.DIComponent;
import lv.javaguru.java2.library.dependency_injection.DIDependency;

@DIComponent
public class RemoveBookUIAction implements UIAction {

	@DIDependency private RemoveBookService removeBookService;

	@Override
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter book id to remove: ");
		Long bookId = Long.parseLong(scanner.nextLine());
		RemoveBookRequest request = new RemoveBookRequest(bookId);
		RemoveBookResponse response = removeBookService.execute(request);

		if (response.hasErrors()) {
			response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
		} else {
			if (response.isBookRemoved()) {
				System.out.println("Your book was removed from list.");
			} else {
				System.out.println("Your book not removed from list.");
			}
		}
	}
}