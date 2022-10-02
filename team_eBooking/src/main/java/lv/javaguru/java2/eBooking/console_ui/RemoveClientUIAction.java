package lv.javaguru.java2.eBooking.console_ui;

import lv.javaguru.java2.eBooking.core.request.RemoveClientRequest;
import lv.javaguru.java2.eBooking.core.service.RemoveClientService;

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
        removeClientService.execute(request);
        System.out.println("Client removed from the list");
    }
}
