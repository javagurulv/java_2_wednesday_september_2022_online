package lv.javaguru.java2.eBooking.core.requests.appointment_request;

public class AppointmentRemoveRequest {

    private Long AppointmentId;

    public AppointmentRemoveRequest(Long appointmentId) {
        this.AppointmentId = appointmentId;
    }

    public Long getAppointmentId() {
        return AppointmentId;
    }
}
