package lv.javaguru.java2.eBooking.console_ui;

import lv.javaguru.java2.eBooking.core.requests.client_request.RemoveClientRequest;
import lv.javaguru.java2.eBooking.core.responses.client.RemoveClientResponse;
import lv.javaguru.java2.eBooking.core.services.client.remove.RemoveClientService;

import java.util.Scanner;

public class RemoveClientUIAction implements UIAction {
    private RemoveClientService removeClientService;


    public RemoveClientUIAction(RemoveClientService removeClientService) {
        this.removeClientService = removeClientService;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter client ID to remove");
        Long clientId = Long.parseLong(scanner.nextLine());
        RemoveClientRequest request = new RemoveClientRequest(clientId);
        RemoveClientResponse response = removeClientService.execute(request);

        if (response.hasError()) {
            response.getErrors().forEach(coreError -> System.out.println(
                    "Error:" + " " +
                            coreError.getField() + " " + coreError.getClientValidationMessage()));
        } else {
            if (response.isClientRemoved()) {
                System.out.println("Client removed from database");
            } else {
                System.out.println("Client is not removed from database");
            }
        }
    }
}
