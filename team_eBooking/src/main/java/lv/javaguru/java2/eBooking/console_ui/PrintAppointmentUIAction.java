package lv.javaguru.java2.eBooking.console_ui;

import lv.javaguru.java2.eBooking.core.request.AddAppointmentRequest;
import lv.javaguru.java2.eBooking.core.request.GetAllAppointmentRequest;
import lv.javaguru.java2.eBooking.core.response.AddAppointmentResponse;
import lv.javaguru.java2.eBooking.core.response.GetAllAppointmentResponse;
import lv.javaguru.java2.eBooking.core.service.GetAllAppointmentsService;

public class PrintAppointmentUIAction implements UIAction {
    private GetAllAppointmentsService getAllAppointmentsService;

    public PrintAppointmentUIAction(GetAllAppointmentsService getAllAppointmentsService) {
        this.getAllAppointmentsService = getAllAppointmentsService;
    }

    public void execute() {
        System.out.println("Check all appointments");
       GetAllAppointmentRequest request = new GetAllAppointmentRequest();
        GetAllAppointmentResponse response = getAllAppointmentsService.execute(request);
        response.getAppointmentList().forEach(System.out::println);
    }
}
