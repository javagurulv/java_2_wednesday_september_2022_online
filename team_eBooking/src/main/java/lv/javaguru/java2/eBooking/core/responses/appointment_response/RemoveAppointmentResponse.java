package lv.javaguru.java2.eBooking.core.responses.appointment_response;

import lv.javaguru.java2.eBooking.core.responses.CoreResponse;

public class RemoveAppointmentResponse extends CoreResponse {

    private boolean isAppointmentRemoved;

    public RemoveAppointmentResponse(boolean isAppointmentRemoved) {
        this.isAppointmentRemoved = isAppointmentRemoved;
    }

    public boolean isAppointmentRemoved() {
        return isAppointmentRemoved;
    }
}
