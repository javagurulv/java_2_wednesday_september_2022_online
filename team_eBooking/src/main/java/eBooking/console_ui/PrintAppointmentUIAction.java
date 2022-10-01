package eBooking.console_ui;

import eBooking.service.GetAllAppointmentsService;

public class PrintAppointmentUIAction implements UIAction {
    private GetAllAppointmentsService getAllAppointmentsService;

    public PrintAppointmentUIAction(GetAllAppointmentsService getAllAppointmentsService) {
        this.getAllAppointmentsService = getAllAppointmentsService;
    }

    public void execute() {
        System.out.println("Check all appointments");
        getAllAppointmentsService.execute();
    }
}
