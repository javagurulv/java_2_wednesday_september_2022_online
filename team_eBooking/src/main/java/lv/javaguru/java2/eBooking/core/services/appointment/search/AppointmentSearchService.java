package lv.javaguru.java2.eBooking.core.services.appointment.search;

import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.domain.Appointment;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.SearchAppointmentRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.appointment.SearchAppointmentResponse;

import java.util.List;

public class AppointmentSearchService {
    private Database database;
    private AppointmentSearchRequestValidator validator;

    public AppointmentSearchService(Database database, AppointmentSearchRequestValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public SearchAppointmentResponse execute(SearchAppointmentRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchAppointmentResponse(errors, null);
        }
        List<Appointment> appointment = null;
        if (request.isMasterNameProvided() && !request.isTypeOfServiceProvided()) {
            appointment = database.findAppointmentByMasterName(request.getMasterName());
        }
        if (request.isTypeOfServiceProvided() && !request.isMasterNameProvided()) {
            appointment = database.findAppointmentByTypeOfService(request.getTypeOfService());
        }
        if (request.isMasterNameProvided() && request.isTypeOfServiceProvided()) {
            appointment = database.findAppointmentByMasterNameAndTypeOfService(request.getMasterName(),
                    request.getTypeOfService());
        }
        System.out.println("Appointment not found");
        return new SearchAppointmentResponse(null, appointment);
    }
}
