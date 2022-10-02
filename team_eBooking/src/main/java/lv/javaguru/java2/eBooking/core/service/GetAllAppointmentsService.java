package lv.javaguru.java2.eBooking.core.service;

import lv.javaguru.java2.eBooking.Appointment;
import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.request.GetAllAppointmentRequest;
import lv.javaguru.java2.eBooking.core.response.GetAllAppointmentResponse;

import java.util.List;

public class GetAllAppointmentsService {
    private Database database;

    public GetAllAppointmentsService(Database database) {
        this.database = database;
    }

    public GetAllAppointmentResponse execute(GetAllAppointmentRequest request) {

        List<Appointment> appointmentList = database.getAllAppointments();

        return new GetAllAppointmentResponse(appointmentList);

    }
}