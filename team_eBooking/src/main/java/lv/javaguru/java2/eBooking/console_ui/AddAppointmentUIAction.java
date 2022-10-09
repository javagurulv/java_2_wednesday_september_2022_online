package lv.javaguru.java2.eBooking.console_ui;

import lv.javaguru.java2.eBooking.core.requests.appointment_request.AddAppointmentRequest;
import lv.javaguru.java2.eBooking.core.services.appointment_service.AddAppointmentService;

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
        AddAppointmentRequest request = new AddAppointmentRequest(masterName,typeOfService);
        addAppointmentService.execute(request);
        System.out.println("Appointment added to the list");
    }
}
