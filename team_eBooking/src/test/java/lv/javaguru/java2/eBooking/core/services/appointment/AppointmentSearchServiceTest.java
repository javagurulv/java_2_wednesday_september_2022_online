package lv.javaguru.java2.eBooking.core.services.appointment;

import lv.javaguru.java2.eBooking.core.database.ClientRepository;
import lv.javaguru.java2.eBooking.core.domain.Appointment;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.AppointmentSearchRequest;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.Ordering;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.Paging;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.appointment.AppointmentSearchResponse;
import lv.javaguru.java2.eBooking.core.services.validators.AppointmentSearchValidator;
import lv.javaguru.java2.eBooking.core.services.validators.AppointmentValidationResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class AppointmentSearchServiceTest {

    @Mock private ClientRepository clientRepository;
    @Mock private AppointmentSearchValidator validator;
    @InjectMocks private AppointmentSearchService service;

    @Test

    public void shouldReturnErrorsWhenValidatorFails() {
        AppointmentSearchRequest request = new AppointmentSearchRequest(null, null);
        List<CoreError> errors = new ArrayList<>();

        errors.add(new CoreError("Master name", AppointmentValidationResult.MASTERNAME_MUST_NOT_BE_EMPTY));
        errors.add(new CoreError("Type of service", AppointmentValidationResult.SERVICETYPE_MUST_NOT_BE_EMPTY));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AppointmentSearchResponse response = service.execute(request);
        assertTrue(response.hasError());
        assertEquals(response.getErrors().size(), 2);

        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());
        Mockito.verifyNoInteractions(clientRepository);
    }

    @Test
    public void shouldSearchBySortedMasterNameAndPagingFirstPage() {
        Ordering ordering = new Ordering("Master name");
        Paging paging = new Paging(1, 1);
        AppointmentSearchRequest request = new AppointmentSearchRequest(
                "Master name",
                null,
                paging,
                ordering);
        Mockito
                .when(validator.validate(request))
                .thenReturn(new ArrayList<>());

        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment("Master name1", null));
        appointments.add(new Appointment("Master name2", null));

        Mockito
                .when(clientRepository.findAppointmentByMasterName("Master name"))
                .thenReturn(appointments);

        AppointmentSearchResponse response = service.execute(request);

        assertFalse(response.hasError());
        assertEquals(response.getAppointments().size(),1);

    }

    @Test
    public void shouldSearchBySortedMasterNameAndPagingSecondPage() {
        Ordering ordering = new Ordering("Master name");
        Paging paging = new Paging(2, 1);
        AppointmentSearchRequest request = new AppointmentSearchRequest(
                "Master name1",
                null,
                paging,
                ordering);
        Mockito
                .when(validator.validate(request))
                .thenReturn(new ArrayList<>());

        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment("Master name1", null));
        appointments.add(new Appointment("Master name2", null));

        Mockito
                .when(clientRepository.findAppointmentByMasterName("Master name1"))
                .thenReturn(appointments);

        AppointmentSearchResponse response = service.execute(request);

        assertFalse(response.hasError());
        assertEquals(response.getAppointments().size(),1);

    }

    @Test
    public void shouldSearchBySortedTypeOfServiceAndPagingFirstPage() {
        Ordering ordering = new Ordering("Type of service");
        Paging paging = new Paging(1, 1);
        AppointmentSearchRequest request = new AppointmentSearchRequest(
                null,
                "Type of service",
                paging,
                ordering);
        Mockito
                .when(validator.validate(request))
                .thenReturn(new ArrayList<>());

        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment(null, "Type of service"));
        Mockito
                .when(clientRepository.findAppointmentByTypeOfService("Type of service"))
                .thenReturn(appointments);

        AppointmentSearchResponse response = service.execute(request);

        assertFalse(response.hasError());
        assertEquals(response.getAppointments().size(),1);
    }

    @Test
    public void shouldSearchBySortedTypeOfServiceAndPagingSecondPage() {
        Ordering ordering = new Ordering("Type of service");
        Paging paging = new Paging(2, 1);
        AppointmentSearchRequest request = new AppointmentSearchRequest(
                null,
                "Type of service1",
                paging,
                ordering);
        Mockito
                .when(validator.validate(request))
                .thenReturn(new ArrayList<>());

        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment(null, "Type of service1"));
        appointments.add(new Appointment(null, "Type of service2"));

        Mockito
                .when(clientRepository.findAppointmentByTypeOfService("Type of service1"))
                .thenReturn(appointments);

        AppointmentSearchResponse response = service.execute(request);

        assertFalse(response.hasError());
        assertEquals(response.getAppointments().size(),1);
    }
}