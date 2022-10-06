package lv.javaguru.java2.eBooking.console_ui;

import lv.javaguru.java2.eBooking.core.request.AddClientRequest;
import lv.javaguru.java2.eBooking.core.response.AddClientResponse;
import lv.javaguru.java2.eBooking.core.service.AddClientService;

import java.util.Scanner;

public class AddClientUIAction implements UIAction {
    private AddClientService addClientService;

    public AddClientUIAction(AddClientService addClientService) {
        this.addClientService = addClientService;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your e-mail");
        String clientEmail = scanner.nextLine();
        System.out.println("Enter phone number");
        String clientPhoneNumber = scanner.nextLine();
        AddClientRequest request = new AddClientRequest(clientEmail, clientPhoneNumber);
        AddClientResponse response = addClientService.execute(request);

        if (response.hasError()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField()
                            + " "
                            + coreError.getMessage()));

        } else {
            System.out.println("New client id:" + response.getNewClient().getId());
            System.out.println("Client added to the list");
        }
    }
}
