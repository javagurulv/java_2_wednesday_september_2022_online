package lv.javaguru.java2.eBooking.core.responses.appointment;

import lv.javaguru.java2.eBooking.core.domain.Appointment;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.CoreResponse;
import lv.javaguru.java2.eBooking.core.services.appointment.search.AppointmentSearchService;

import java.util.List;

public class SearchAppointmentResponse extends CoreResponse {

    private List<Appointment> appointments;

    public SearchAppointmentResponse(List<CoreError> errors, List<Appointment> appointments) {
        super(errors);
        this.appointments = appointments;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
}