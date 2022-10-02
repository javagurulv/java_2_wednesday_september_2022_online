package lv.javaguru.java2.eBooking.core.response;

import lv.javaguru.java2.eBooking.Appointment;

import java.util.List;

public class GetAllAppointmentResponse {

    private List<Appointment> appointmentList;

    public GetAllAppointmentResponse(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }
}
