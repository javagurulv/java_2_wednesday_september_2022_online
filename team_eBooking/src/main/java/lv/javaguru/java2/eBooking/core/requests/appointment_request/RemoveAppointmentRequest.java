package lv.javaguru.java2.eBooking.core.requests.appointment_request;

public class RemoveAppointmentRequest {

    private Long AppointmentId;

    public RemoveAppointmentRequest(Long appointmentId) {
        this.AppointmentId = appointmentId;
    }

    public Long getAppointmentId() {
        return AppointmentId;
    }
}
