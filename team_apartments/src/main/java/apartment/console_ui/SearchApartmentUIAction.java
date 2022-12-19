package apartment.console_ui;

import apartment.Apartment;
import apartment.core.request.SearchApartmentRequest;
import apartment.core.response.SearchApartmentResponse;
import apartment.core.services.SearchApartmentService;

import java.util.Scanner;

public class SearchApartmentUIAction implements UIAction{

    private SearchApartmentService searchApartmentService;

    public SearchApartmentUIAction (SearchApartmentService searchApartmentService){
        this.searchApartmentService = searchApartmentService;
    }

    @Override
    public void execute(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter apartment's address");
        String address = scanner.nextLine();
        System.out.println("Enter apartment's room amount");
        Long roomsAmount = scanner.nextLong();
        System.out.println("Enter apartment's floor");
        Long floor = scanner.nextLong();
        System.out.println("Enter apartment's floor");
        Long area = scanner.nextLong();

        SearchApartmentRequest request = new SearchApartmentRequest(address, roomsAmount, floor, area);
        SearchApartmentResponse response = searchApartmentService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            response.getApartments().forEach(Apartment::toString);
        }
    }
}
