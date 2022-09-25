package eBooking.console_ui;

import eBooking.database.Database;

public class PrintClientUIAction implements UIAction {
private Database database;

    public PrintClientUIAction(Database database) {
        this.database = database;
    }
    public void execute() {
        System.out.println("Client list");
        if (database.getAllClients().isEmpty()) {
            System.out.println("Client list is empty");
        } else {
            database.getAllClients().forEach(System.out::println);
        }
    }
}
