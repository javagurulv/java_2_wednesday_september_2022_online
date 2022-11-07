package lv.javaguru.java2.eBooking.console_ui;

import lv.javaguru.java2.eBooking.core.requests.client_request.ClientAddRequest;
import lv.javaguru.java2.eBooking.core.responses.client.ClientAddResponse;
import lv.javaguru.java2.eBooking.core.services.client.ClientAddService;
import lv.javaguru.java2.eBooking.dependency_injection.DIComponent;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class AddClientUIAction implements UIAction {
    private ClientAddService clientAddService;

    public AddClientUIAction(ClientAddService clientAddService) {
        this.clientAddService = clientAddService;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your e-mail");
        String clientEmail = scanner.nextLine();
        System.out.println("Enter phone number");
        String clientPhoneNumber = scanner.nextLine();
        ClientAddRequest request = new ClientAddRequest(clientEmail, clientPhoneNumber);
        ClientAddResponse response = clientAddService.execute(request);

        if (response.hasError()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField()
                            + " "
                            + coreError.getClientValidationMessage()));

        } else {
            System.out.println("New client id:" + response.getNewClient().getId());
            System.out.println("Client added to the list");
        }
    }
}
