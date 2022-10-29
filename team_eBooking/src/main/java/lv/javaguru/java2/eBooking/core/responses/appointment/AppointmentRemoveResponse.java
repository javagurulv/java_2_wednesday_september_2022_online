package lv.javaguru.java2.eBooking.core.responses.appointment;

import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.CoreResponse;

import java.util.List;

public class AppointmentRemoveResponse extends CoreResponse {

    private boolean isAppointmentRemoved;

    public AppointmentRemoveResponse(List<CoreError> errors){
        super(errors);
    }

    public AppointmentRemoveResponse(boolean isAppointmentRemoved) {
        this.isAppointmentRemoved = isAppointmentRemoved;
    }

    public boolean isAppointmentRemoved() {
        return isAppointmentRemoved;
    }
}
