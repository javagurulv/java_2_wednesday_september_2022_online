package lv.javaguru.java2.eBooking.core.services.appointment_service;

import lv.javaguru.java2.eBooking.core.domain.Appointment;
import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.AddAppointmentRequest;
import lv.javaguru.java2.eBooking.core.responses.appointment_response.AddAppointmentResponse;

public class AddAppointmentService {

    private Database database;

    public AddAppointmentService(Database database) {
        this.database = database;
    }

    public AddAppointmentResponse execute(AddAppointmentRequest addAppointmentRequest){
        Appointment newAppointment = new Appointment(addAppointmentRequest.getMasterName(),
                                                     addAppointmentRequest.getTypeOfService());
        database.saveAppointment(newAppointment);
        return new AddAppointmentResponse(newAppointment);
    }
}
