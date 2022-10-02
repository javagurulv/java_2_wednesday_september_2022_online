package lv.javaguru.java2.eBooking.core.response;

public class RemoveAppointmentResponse {

    private boolean isAppointmentRemoved;

    public RemoveAppointmentResponse(boolean isAppointmentRemoved) {
        this.isAppointmentRemoved = isAppointmentRemoved;
    }

    public boolean isAppointmentRemoved() {
        return isAppointmentRemoved;
    }
}
