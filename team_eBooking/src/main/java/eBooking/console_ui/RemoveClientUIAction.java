package eBooking.console_ui;

import eBooking.service.RemoveClientService;

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
        removeClientService.removeClient(clientId);
        System.out.println("Client removed from the list");
    }
}
