package lv.javaguru.java2.eBooking.core.request;

public class RemoveAppointmentRequest {

    private Long AppointmentId;

    public RemoveAppointmentRequest(Long appointmentId) {
        AppointmentId = appointmentId;
    }

    public Long getAppointmentId() {
        return AppointmentId;
    }
}
