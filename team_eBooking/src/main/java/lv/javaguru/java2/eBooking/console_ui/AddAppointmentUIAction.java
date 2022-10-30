package lv.javaguru.java2.eBooking.console_ui;

import lv.javaguru.java2.eBooking.core.requests.appointment_request.AppointmentAddRequest;
import lv.javaguru.java2.eBooking.core.responses.appointment.AppointmentAddResponse;
import lv.javaguru.java2.eBooking.core.services.appointment.AppointmentAddService;

import java.util.Scanner;

public class AddAppointmentUIAction implements UIAction {
    private AppointmentAddService appointmentAddService;

    public AddAppointmentUIAction(AppointmentAddService appointmentAddService) {
        this.appointmentAddService = appointmentAddService;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose master name");
        String masterName = scanner.nextLine();
        System.out.println("Choose type of service");
        String typeOfService = scanner.nextLine();
        System.out.println("Choose available date");
        AppointmentAddRequest request = new AppointmentAddRequest(masterName, typeOfService);
        AppointmentAddResponse response = appointmentAddService.execute(request);

        if (response.hasError()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " +
                            coreError.getAppointmentValidationMessage()));
        }

        System.out.println("Appointment added to the list");
    }
}
