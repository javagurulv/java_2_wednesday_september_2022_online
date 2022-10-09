package lv.javaguru.java2.eBooking.core.services.appointment_service;

import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.RemoveAppointmentRequest;
import lv.javaguru.java2.eBooking.core.responses.appointment_response.RemoveAppointmentResponse;

public class RemoveAppointmentService {
    private Database database;

    public RemoveAppointmentService(Database database) {
        this.database = database;
    }

    public RemoveAppointmentResponse execute(RemoveAppointmentRequest request){

       boolean isAppointmentRemoved =  database.deleteAppointmentById(request.getAppointmentId());
       return new RemoveAppointmentResponse(isAppointmentRemoved);
    }
}
