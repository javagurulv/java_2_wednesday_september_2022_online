package lv.javaguru.java2.eBooking.core.service;

import lv.javaguru.java2.eBooking.Appointment;
import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.request.AddAppointmentRequest;
import lv.javaguru.java2.eBooking.core.response.AddAppointmentResponse;

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
