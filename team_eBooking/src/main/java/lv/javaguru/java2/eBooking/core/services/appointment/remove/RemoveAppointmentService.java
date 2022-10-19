package lv.javaguru.java2.eBooking.core.services.appointment.remove;

import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.RemoveAppointmentRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.appointment.RemoveAppointmentResponse;
import lv.javaguru.java2.eBooking.core.services.appointment.add.AppointmentValidationResult;

import java.util.ArrayList;
import java.util.List;

public class RemoveAppointmentService {
    private Database database;

    public RemoveAppointmentService(Database database) {
        this.database = database;
    }

    public RemoveAppointmentResponse execute(RemoveAppointmentRequest request){
        if(request.getAppointmentId() == null){
            CoreError error = new CoreError("id: ", AppointmentValidationResult.APPOINTMENT_ID_MUST_NOT_BE_EMPTY);
            List<CoreError> errors = new ArrayList<>();
            errors.add(error);
            return new RemoveAppointmentResponse(errors);
        }

       boolean isAppointmentRemoved =  database.deleteAppointmentById(request.getAppointmentId());
       return new RemoveAppointmentResponse(isAppointmentRemoved);
    }
}
