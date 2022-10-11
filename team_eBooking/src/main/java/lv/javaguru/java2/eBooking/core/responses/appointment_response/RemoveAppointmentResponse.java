package lv.javaguru.java2.eBooking.core.responses.appointment_response;

import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.CoreResponse;

import java.util.List;

public class RemoveAppointmentResponse extends CoreResponse {

    private boolean isAppointmentRemoved;

    public RemoveAppointmentResponse(List<CoreError> errors){
        super(errors);
    }

    public RemoveAppointmentResponse(boolean isAppointmentRemoved) {
        this.isAppointmentRemoved = isAppointmentRemoved;
    }

    public boolean isAppointmentRemoved() {
        return isAppointmentRemoved;
    }
}
