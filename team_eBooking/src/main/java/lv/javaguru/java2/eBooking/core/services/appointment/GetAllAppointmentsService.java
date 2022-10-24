package lv.javaguru.java2.eBooking.core.services.appointment;

import lv.javaguru.java2.eBooking.core.domain.Appointment;
import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.GetAllAppointmentRequest;
import lv.javaguru.java2.eBooking.core.responses.appointment.GetAllAppointmentResponse;

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