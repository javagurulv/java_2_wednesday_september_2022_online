package lv.javaguru.java2.eBooking.core.responses.appointment;

import lv.javaguru.java2.eBooking.core.domain.Appointment;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.CoreResponse;

import java.util.List;

public class AppointmentAddResponse extends CoreResponse {

    private Appointment newAppointment;

    public AppointmentAddResponse(Appointment newAppointment) {
        this.newAppointment = newAppointment;
    }

    public AppointmentAddResponse(List<CoreError> errors) {
        super(errors);
    }

    public Appointment getNewAppointment() {
        return newAppointment;
    }
}
