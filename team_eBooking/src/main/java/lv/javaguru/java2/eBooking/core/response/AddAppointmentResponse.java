package lv.javaguru.java2.eBooking.core.response;

import lv.javaguru.java2.eBooking.Appointment;

public class AddAppointmentResponse {

    private Appointment newAppointment;

    public AddAppointmentResponse(Appointment newAppointment) {
        this.newAppointment = newAppointment;
    }

    public Appointment getNewAppointment() {
        return newAppointment;
    }
}
