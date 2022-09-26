package eBooking.console_ui;

import eBooking.database.Database;
import eBooking.service.RemoveAppointmentService;

import java.util.Scanner;

public class RemoveAppointmentUIAction implements UIAction {
   private RemoveAppointmentService removeAppointmentService;

    public RemoveAppointmentUIAction(RemoveAppointmentService removeAppointmentService) {
        this.removeAppointmentService = removeAppointmentService;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Remove appointment by id");
        Long appointmentId = Long.parseLong(scanner.nextLine());
       removeAppointmentService.execute(appointmentId);
        System.out.println("Appointment removed from the list");
    }
}
