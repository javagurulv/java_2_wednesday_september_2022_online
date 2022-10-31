package lv.javaguru.java2.eBooking.console_ui;

import lv.javaguru.java2.eBooking.core.requests.client_request.ClientRemoveRequest;
import lv.javaguru.java2.eBooking.core.responses.client.ClientRemoveResponse;
import lv.javaguru.java2.eBooking.core.services.client.ClientRemoveService;

import java.util.Scanner;

public class RemoveClientUIAction implements UIAction {
    private ClientRemoveService clientRemoveService;


    public RemoveClientUIAction(ClientRemoveService clientRemoveService) {
        this.clientRemoveService = clientRemoveService;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter client ID to remove");
        Long clientId = Long.parseLong(scanner.nextLine());
        ClientRemoveRequest request = new ClientRemoveRequest(clientId);
        ClientRemoveResponse response = clientRemoveService.execute(request);

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
