package lv.javaguru.java2.eBooking.console_ui;

import lv.javaguru.java2.eBooking.core.requests.appointment_request.RemoveAppointmentRequest;
import lv.javaguru.java2.eBooking.core.responses.appointment.RemoveAppointmentResponse;
import lv.javaguru.java2.eBooking.core.services.appointment.remove.RemoveAppointmentService;

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
        RemoveAppointmentResponse response = removeAppointmentService.execute(request);
        if (response.hasError()) {
            response.getErrors().forEach(coreError -> System.out.println(coreError.getField() + " "
                    + coreError.getAppointmentValidationMessage()));
        } else {
            if (response.isAppointmentRemoved()) {
                System.out.println("Appointment is removed from database");
            } else {
                System.out.println("Appointment wasn't removed from database");
            }
        }
    }
}
