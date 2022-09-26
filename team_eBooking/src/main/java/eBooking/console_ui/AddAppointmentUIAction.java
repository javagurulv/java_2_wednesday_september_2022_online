package eBooking.console_ui;

import eBooking.Appointment;
import eBooking.database.Database;
import eBooking.service.AddAppointmentService;

import java.util.Scanner;

public class AddAppointmentUIAction implements UIAction {
   private AddAppointmentService addAppointmentService;

    public AddAppointmentUIAction(AddAppointmentService addAppointmentService) {
        this.addAppointmentService = addAppointmentService;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose master name");
        String masterName = scanner.nextLine();
        System.out.println("Choose type of service");
        String typeOfService = scanner.nextLine();
        System.out.println("Choose available date");
        addAppointmentService.execute(masterName, typeOfService);
        System.out.println("Appointment added to the list");
    }
}
