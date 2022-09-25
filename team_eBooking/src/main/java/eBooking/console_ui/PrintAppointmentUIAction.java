package eBooking.console_ui;

import eBooking.database.Database;

public class PrintAppointmentUIAction implements UIAction {
    private Database database;

    public PrintAppointmentUIAction(Database database) {
        this.database = database;
    }
    public void execute() {
        System.out.println("Check all appointments");
        if (database.getAllAppointments().isEmpty()) {
            System.out.println("List is empty");
        } else {
            database.getAllClients().forEach(System.out::println);
        }
    }
}
