package lv.javaguru.java2.eBooking.console_ui;

import lv.javaguru.java2.eBooking.core.requests.appointment_request.AppointmentRemoveRequest;
import lv.javaguru.java2.eBooking.core.responses.appointment.AppointmentRemoveResponse;
import lv.javaguru.java2.eBooking.core.services.appointment.AppointmentRemoveService;
import lv.javaguru.java2.eBooking.dependency_injection.DIComponent;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class RemoveAppointmentUIAction implements UIAction {
    private AppointmentRemoveService appointmentRemoveService;

    public RemoveAppointmentUIAction(AppointmentRemoveService appointmentRemoveService) {
        this.appointmentRemoveService = appointmentRemoveService;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Remove appointment by id");
        Long appointmentId = Long.parseLong(scanner.nextLine());
        AppointmentRemoveRequest request = new AppointmentRemoveRequest(appointmentId);
        AppointmentRemoveResponse response = appointmentRemoveService.execute(request);
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
