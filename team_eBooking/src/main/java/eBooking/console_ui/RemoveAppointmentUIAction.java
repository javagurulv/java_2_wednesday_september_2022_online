package eBooking.console_ui;

import eBooking.Database;

import java.util.Scanner;

public class RemoveAppointmentUIAction {
    private Database database;

    public RemoveAppointmentUIAction(Database database) {
        this.database = database;
    }

    public void executeRemoveAppointment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Remove appointment by id");
        Long appointmentId = Long.parseLong(scanner.nextLine());
        database.deleteAppointmentById(appointmentId);
        System.out.println("Appointment removed from the list");
    }
}
