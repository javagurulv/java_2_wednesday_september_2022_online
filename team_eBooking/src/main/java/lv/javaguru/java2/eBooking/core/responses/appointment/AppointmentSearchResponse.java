package lv.javaguru.java2.eBooking.core.responses.appointment;

import lv.javaguru.java2.eBooking.core.domain.Appointment;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.CoreResponse;

import java.util.List;

public class AppointmentSearchResponse extends CoreResponse {

    private List<Appointment> appointments;

    public AppointmentSearchResponse(List<CoreError> errors, List<Appointment> appointments) {
        super(errors);
        this.appointments = appointments;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
}
