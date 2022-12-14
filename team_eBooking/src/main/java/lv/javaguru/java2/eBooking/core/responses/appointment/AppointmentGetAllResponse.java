package lv.javaguru.java2.eBooking.core.responses.appointment;

import lv.javaguru.java2.eBooking.core.domain.Appointment;
import lv.javaguru.java2.eBooking.core.responses.CoreResponse;

import java.util.List;

public class AppointmentGetAllResponse extends CoreResponse {

    private List<Appointment> appointmentList;

    public AppointmentGetAllResponse(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }
}
