package lv.javaguru.java2.eBooking.console_ui;

import lv.javaguru.java2.eBooking.core.requests.appointment_request.AppointmentGetAllRequest;
import lv.javaguru.java2.eBooking.core.responses.appointment.AppointmentGetAllResponse;
import lv.javaguru.java2.eBooking.core.services.appointment.AppointmentGetAllService;

public class PrintAppointmentUIAction implements UIAction {
    private AppointmentGetAllService appointmentGetAllService;

    public PrintAppointmentUIAction(AppointmentGetAllService appointmentGetAllService) {
        this.appointmentGetAllService = appointmentGetAllService;
    }

    public void execute() {
        System.out.println("Check all appointments");
       AppointmentGetAllRequest request = new AppointmentGetAllRequest();
        AppointmentGetAllResponse response = appointmentGetAllService.execute(request);
        response.getAppointmentList().forEach(System.out::println);
    }
}
