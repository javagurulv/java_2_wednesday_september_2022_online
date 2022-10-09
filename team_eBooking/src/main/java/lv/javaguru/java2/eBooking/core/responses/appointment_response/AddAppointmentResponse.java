package lv.javaguru.java2.eBooking.core.responses.appointment_response;

import lv.javaguru.java2.eBooking.core.domain.Appointment;
import lv.javaguru.java2.eBooking.core.responses.CoreResponse;

public class AddAppointmentResponse extends CoreResponse {

    private Appointment newAppointment;

    public AddAppointmentResponse(Appointment newAppointment) {
        this.newAppointment = newAppointment;
    }

    public Appointment getNewAppointment() {
        return newAppointment;
    }
}
