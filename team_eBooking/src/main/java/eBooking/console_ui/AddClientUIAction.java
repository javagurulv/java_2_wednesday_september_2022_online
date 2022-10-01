package eBooking.console_ui;

import eBooking.service.AddClientService;

import java.util.Scanner;

public class AddClientUIAction implements UIAction{
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
        addClientService.execute(clientEmail,clientPhoneNumber);
        System.out.println("Client added to the list");
    }
}