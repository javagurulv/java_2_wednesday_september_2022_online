package lv.javaguru.java2.eBooking.core.services.appointment;

import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.AppointmentRemoveRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.appointment.AppointmentRemoveResponse;
import lv.javaguru.java2.eBooking.core.services.validators.AppointmentRemoveValidator;
import lv.javaguru.java2.eBooking.core.services.validators.AppointmentValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class AppointmentRemoveService {
    @Autowired
    private Database database;
    @Autowired
    private AppointmentRemoveValidator validator;

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
