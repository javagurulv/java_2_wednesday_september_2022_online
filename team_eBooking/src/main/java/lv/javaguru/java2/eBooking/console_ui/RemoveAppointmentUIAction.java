package lv.javaguru.java2.eBooking.console_ui;

import lv.javaguru.java2.eBooking.core.request.RemoveAppointmentRequest;
import lv.javaguru.java2.eBooking.core.response.RemoveAppointmentResponse;
import lv.javaguru.java2.eBooking.core.service.RemoveAppointmentService;

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
        RemoveAppointmentRequest request = new RemoveAppointmentRequest(appointmentId);
       removeAppointmentService.execute(request);
       System.out.println("Appointment removed from the list");
    }
}
