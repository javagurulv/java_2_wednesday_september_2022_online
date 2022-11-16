package lv.javaguru.java2.eBooking.core.services.appointment;

import lv.javaguru.java2.eBooking.core.domain.Appointment;
import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.AppointmentGetAllRequest;
import lv.javaguru.java2.eBooking.core.responses.appointment.AppointmentGetAllResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AppointmentGetAllService {
    @Autowired
    private Database database;

    public AppointmentGetAllResponse execute(AppointmentGetAllRequest request) {

        List<Appointment> appointmentList = database.getAllAppointments();

        return new AppointmentGetAllResponse(appointmentList);

    }
}