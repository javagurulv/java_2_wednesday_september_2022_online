package lv.javaguru.java2.eBooking.core.services.appointment;

import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.AppointmentRemoveRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.appointment.AppointmentRemoveResponse;
import lv.javaguru.java2.eBooking.core.services.validators.AppointmentValidationResult;

import java.util.ArrayList;
import java.util.List;

public class AppointmentRemoveService {
    private Database database;

    public AppointmentRemoveService(Database database) {
        this.database = database;
    }

    public AppointmentRemoveResponse execute(AppointmentRemoveRequest request){
        if(request.getAppointmentId() == null){
            CoreError error = new CoreError("id: ", AppointmentValidationResult.APPOINTMENT_ID_MUST_NOT_BE_EMPTY);
            List<CoreError> errors = new ArrayList<>();
            errors.add(error);
            return new AppointmentRemoveResponse(errors);
        }

       boolean isAppointmentRemoved =  database.deleteAppointmentById(request.getAppointmentId());
       return new AppointmentRemoveResponse(isAppointmentRemoved);
    }
}
