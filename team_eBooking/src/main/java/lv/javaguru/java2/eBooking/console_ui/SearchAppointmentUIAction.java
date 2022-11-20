package lv.javaguru.java2.eBooking.console_ui;

import lv.javaguru.java2.eBooking.core.requests.appointment_request.Ordering;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.Paging;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.AppointmentSearchRequest;
import lv.javaguru.java2.eBooking.core.responses.appointment.AppointmentSearchResponse;
import lv.javaguru.java2.eBooking.core.services.appointment.AppointmentSearchService;
import lv.javaguru.java2.eBooking.dependency_injection.DIComponent;
import org.springframework.stereotype.Component;


import java.util.Scanner;
@Component
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

        System.out.println("Order by Master name || Type of service");
        String orderBy = scanner.nextLine();
        Ordering ordering = new Ordering(orderBy);

        System.out.println("Enter page number: ");
        Integer pageNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter page size;");
        Integer pageSize = Integer.parseInt(scanner.nextLine());
        Paging paging = new Paging(pageNumber, pageSize);

        AppointmentSearchRequest request = new AppointmentSearchRequest(masterName,typeOfService,paging,ordering);
        AppointmentSearchResponse response = appointmentSearchService.execute(request);

        if(response.hasError()){
            response.getErrors().forEach(coreError-> System.out.println(coreError.getField() +
                    " " + coreError.getAppointmentValidationMessage()));
        } else {
            response.getAppointments().forEach(appointment -> System.out.println(appointment));
        }
    }
}
