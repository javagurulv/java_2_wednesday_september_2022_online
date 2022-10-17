package lv.javaguru.java2.eBooking.core.services.appointment.search;

import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.domain.Appointment;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.Ordering;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.SearchAppointmentRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.appointment.SearchAppointmentResponse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class AppointmentSearchService {
    private Database database;
    private AppointmentSearchRequestValidator validator;

    public AppointmentSearchService(Database database, AppointmentSearchRequestValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public SearchAppointmentResponse execute(SearchAppointmentRequest request, Ordering ordering) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchAppointmentResponse(errors, null);
        }
        List<Appointment> appointments = search(request);

        if(ordering != null) {
            appointments = orderFunction.apply(appointments,request.getOrdering());
        }

        return new SearchAppointmentResponse(null, appointments);
    }

    private List<Appointment> search(SearchAppointmentRequest request) {
        List<Appointment> appointments = new ArrayList<>();
        if (request.isMasterNameProvided() && !request.isTypeOfServiceProvided()) {
            appointments = database.findAppointmentByMasterName(request.getMasterName());
        }
        if (request.isTypeOfServiceProvided() && !request.isMasterNameProvided()) {
            appointments = database.findAppointmentByTypeOfService(request.getTypeOfService());
        }
        if (request.isMasterNameProvided() && request.isTypeOfServiceProvided()) {
            appointments = database.findAppointmentByMasterNameAndTypeOfService(request.getMasterName(),
                    request.getTypeOfService());
        }
        return appointments;
    }

    private BiFunction<List<Appointment>, Ordering,List<Appointment>> orderFunction =
            (appointments, ordering) -> {
                Comparator<Appointment> appointmentComparator = ordering.getOrderBy().equals("Master name: ")
                        ? Comparator.comparing(Appointment::getMasterName)
                        : Comparator.comparing(Appointment::getTypeOfService);
                return appointments.stream()
                        .sorted(appointmentComparator)
                        .collect(Collectors.toList());
            };
}
