package lv.javaguru.java2.eBooking.console_ui;

import lv.javaguru.java2.eBooking.core.requests.appointment_request.Paging;
import lv.javaguru.java2.eBooking.core.requests.client_request.SearchClientRequest;
import lv.javaguru.java2.eBooking.core.responses.client.SearchClientResponse;
import lv.javaguru.java2.eBooking.core.services.client.search.ClientSearchService;

import java.util.Scanner;

public class SearchClientUIAction implements UIAction {
    private ClientSearchService clientSearchService;

    public SearchClientUIAction(ClientSearchService clientSearchService) {
        this.clientSearchService = clientSearchService;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter client email: ");
        String clientEmail = scanner.nextLine();
        System.out.println("Enter client phone number: ");
        String clientPhoneNumber = scanner.nextLine();

        SearchClientRequest request = new SearchClientRequest(clientEmail, clientPhoneNumber);
        SearchClientResponse response = clientSearchService.execute(request);

        if (response.hasError()) {
            response.getErrors().forEach(coreError -> System.out.println(coreError.getField() +
                    " " + coreError.getClientValidationMessage()));
        } else {
            response.getClients().forEach(client -> System.out.println(client));
        }
    }
}
