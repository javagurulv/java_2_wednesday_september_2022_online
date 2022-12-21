package lv.javaguru.java2.eBooking.core.services.appointment;

import lv.javaguru.java2.eBooking.core.domain.Appointment;
import lv.javaguru.java2.eBooking.core.database.ClientRepository;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.AppointmentAddRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.appointment.AppointmentAddResponse;
import lv.javaguru.java2.eBooking.core.services.validators.AppointmentAddValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppointmentAddService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AppointmentAddValidator validator;

    public AppointmentAddResponse execute(AppointmentAddRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AppointmentAddResponse(errors);
        }
        Appointment newAppointment = new Appointment(request.getMasterName(),
                request.getTypeOfService());
        clientRepository.saveAppointment(newAppointment);
        return new AppointmentAddResponse(newAppointment);
    }
}
