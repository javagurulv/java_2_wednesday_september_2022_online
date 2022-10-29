package lv.javaguru.java2.eBooking.core.services.appointment;

import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.domain.Appointment;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.Ordering;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.Paging;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.AppointmentSearchRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.appointment.AppointmentSearchResponse;
import lv.javaguru.java2.eBooking.core.services.validators.AppointmentSearchValidator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class AppointmentSearchService {
    private Database database;
    private AppointmentSearchValidator validator;

    public AppointmentSearchService(Database database, AppointmentSearchValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public AppointmentSearchResponse execute(AppointmentSearchRequest request, Ordering ordering, Paging paging) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AppointmentSearchResponse(errors, null);
        }
        List<Appointment> appointments = search(request);

        if(ordering != null && paging!=null) {
            appointments = orderAppointments.apply(appointments,request.getOrdering());
            appointments = pagingAppointmentList.apply(appointments,request.getPaging());

        }
        return new AppointmentSearchResponse(null, appointments);
    }

    private List<Appointment> search(AppointmentSearchRequest request) {
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

    private BiFunction<List<Appointment>, Ordering,List<Appointment>> orderAppointments =
            (appointments, ordering) -> {
                Comparator<Appointment> appointmentComparator = ordering.getOrderBy().equals("Master name: ")
                        ? Comparator.comparing(Appointment::getMasterName)
                        : Comparator.comparing(Appointment::getTypeOfService);
                return appointments.stream()
                        .sorted(appointmentComparator)
                        .collect(Collectors.toList());
            };


    private BiFunction<List<Appointment>, Paging, List<Appointment>> pagingAppointmentList =
            (appointments, paging) ->
            {
                int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
                return appointments.stream()
                        .skip(skip)
                        .limit(paging.getPageSize())
                        .collect(Collectors.toList());
            };
}
