package eBooking.console_ui;

import eBooking.Client;
import eBooking.Database;

import java.util.Scanner;

public class AddClientUIAction {
    private Database database;

    public AddClientUIAction(Database database) {
        this.database = database;
    }

    public void executeAddClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your e-mail");
        String clientEmail = scanner.nextLine();
        System.out.println("Enter phone number");
        String clientPhoneNumber = scanner.nextLine();
        database.saveClient(new Client(clientEmail, clientPhoneNumber));
        System.out.println("Client added to the list");
    }
}
