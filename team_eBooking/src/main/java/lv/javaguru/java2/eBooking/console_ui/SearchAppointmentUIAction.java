package lv.javaguru.java2.eBooking.console_ui;

import lv.javaguru.java2.eBooking.core.requests.appointment_request.Ordering;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.SearchAppointmentRequest;
import lv.javaguru.java2.eBooking.core.responses.appointment.SearchAppointmentResponse;
import lv.javaguru.java2.eBooking.core.services.appointment.search.AppointmentSearchService;


import java.util.Scanner;

public class SearchAppointmentUIAction implements UIAction {
    private AppointmentSearchService appointmentSearchService;

    public SearchAppointmentUIAction(AppointmentSearchService appointmentSearchService) {
        this.appointmentSearchService = appointmentSearchService;
    }

    public void execute(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter appointment master name: ");
        String masterName = scanner.nextLine();
        System.out.println("Enter type of service: ");
        String typeOfService = scanner.nextLine();

        System.out.println("Enter sorting by master name: ");
        String orderBy = scanner.nextLine();
        Ordering ordering = new Ordering(orderBy);

        SearchAppointmentRequest request = new SearchAppointmentRequest(masterName,typeOfService,ordering);
        SearchAppointmentResponse response = appointmentSearchService.execute(request,ordering);

        if(response.hasError()){
            response.getErrors().forEach(coreError-> System.out.println(coreError.getField() +
                    " " + coreError.getAppointmentValidationMessage()));
        } else {
            response.getAppointments().forEach(appointment -> System.out.println(appointment));
        }
    }
}
