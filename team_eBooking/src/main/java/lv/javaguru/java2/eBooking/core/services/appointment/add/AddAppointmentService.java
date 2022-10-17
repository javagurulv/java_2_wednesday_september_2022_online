package lv.javaguru.java2.eBooking.core.services.appointment.add;

import lv.javaguru.java2.eBooking.core.domain.Appointment;
import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.AddAppointmentRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.appointment.AddAppointmentResponse;

import java.util.List;

public class AddAppointmentService {

    private Database database;
    private AddAppointmentValidator validator;

    public AddAppointmentService(Database database, AddAppointmentValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public AddAppointmentResponse execute(AddAppointmentRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddAppointmentResponse(errors);
        }
        Appointment newAppointment = new Appointment(request.getMasterName(),
                request.getTypeOfService());
        database.saveAppointment(newAppointment);
        return new AddAppointmentResponse(newAppointment);
    }
}
