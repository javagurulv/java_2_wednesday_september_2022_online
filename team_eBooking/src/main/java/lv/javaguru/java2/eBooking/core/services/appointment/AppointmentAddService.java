package lv.javaguru.java2.eBooking.core.services.appointment;

import lv.javaguru.java2.eBooking.core.domain.Appointment;
import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.AppointmentAddRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.appointment.AppointmentAddResponse;
import lv.javaguru.java2.eBooking.core.services.validators.AppointmentAddValidator;

import java.util.List;

public class AppointmentAddService {

    private Database database;
    private AppointmentAddValidator validator;

    public AppointmentAddService(Database database, AppointmentAddValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public AppointmentAddResponse execute(AppointmentAddRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AppointmentAddResponse(errors);
        }
        Appointment newAppointment = new Appointment(request.getMasterName(),
                request.getTypeOfService());
        database.saveAppointment(newAppointment);
        return new AppointmentAddResponse(newAppointment);
    }
}
