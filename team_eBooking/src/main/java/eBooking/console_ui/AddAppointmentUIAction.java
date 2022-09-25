package eBooking.console_ui;

import eBooking.Appointment;
import eBooking.database.Database;

import java.util.Scanner;

public class AddAppointmentUIAction implements UIAction {
    private Database database;

    public AddAppointmentUIAction(Database database) {
        this.database = database;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose master name");
        String masterName = scanner.nextLine();
        System.out.println("Choose type of service");
        String typeOfService = scanner.nextLine();
        System.out.println("Choose available date");
        database.saveAppointment(new Appointment(masterName, typeOfService));
        System.out.println("Appointment added to the list");
    }
}
