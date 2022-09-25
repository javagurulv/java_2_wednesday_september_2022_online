package eBooking.console_ui;

import eBooking.Database;

public class PrintClientUIAction {
private Database database;

    public PrintClientUIAction(Database database) {
        this.database = database;
    }
    public void executePrintClients() {
        System.out.println("Client list");
        if (database.getAllClients().isEmpty()) {
            System.out.println("Client list is empty");
        } else {
            database.getAllClients().forEach(System.out::println);
        }
    }
}
