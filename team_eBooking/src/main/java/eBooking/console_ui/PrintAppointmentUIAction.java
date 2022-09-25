package eBooking.console_ui;

import eBooking.Database;

public class PrintAppointmentUIAction {
    private Database database;

    public PrintAppointmentUIAction(Database database) {
        this.database = database;
    }
    public void executePrintAppointments() {
        System.out.println("Check all appointments");
        if (database.getAllAppointments().isEmpty()) {
            System.out.println("List is empty");
        } else {
            database.getAllClients().forEach(System.out::println);
        }
    }
}
