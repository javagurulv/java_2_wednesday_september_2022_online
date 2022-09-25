package eBooking.console_ui;

import eBooking.database.Database;

import java.util.Scanner;

public class RemoveClientUIAction implements UIAction {
    private Database database;

    public RemoveClientUIAction(Database database) {
        this.database = database;
    }
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter client ID to remove");
        Long clientId = Long.parseLong(scanner.nextLine());
        database.deleteClientById(clientId);
        System.out.println("Client removed from the list");
    }
}
