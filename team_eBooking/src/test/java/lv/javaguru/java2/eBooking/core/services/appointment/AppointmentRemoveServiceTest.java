package lv.javaguru.java2.eBooking.core.services.appointment;

import lv.javaguru.java2.eBooking.core.database.ClientRepository;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.AppointmentRemoveRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.appointment.AppointmentRemoveResponse;
import lv.javaguru.java2.eBooking.core.services.validators.AppointmentRemoveValidator;
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

@RunWith(MockitoJUnitRunner.Silent.class)
public class AppointmentRemoveServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private AppointmentRemoveValidator validator;

    @InjectMocks
    private AppointmentRemoveService service;

    @Test
    public void shouldReturnResponseWithErrorWhenAppointmentIdIsNotProvided(){
        AppointmentRemoveRequest request = new AppointmentRemoveRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Appointment Id", AppointmentValidationResult.APPOINTMENT_ID_MUST_NOT_BE_EMPTY));
        Mockito.
                when(validator.validate(request)).
                thenReturn(errors);
        AppointmentRemoveResponse response = service.execute(request);
        assertTrue(response.hasError());
        assertEquals(response.getErrors().size(),1);
        assertEquals(response.getErrors().get(0).getField(),"id: ");
        assertEquals(response.getErrors().get(0).getAppointmentValidationMessage(),
                AppointmentValidationResult.APPOINTMENT_ID_MUST_NOT_BE_EMPTY);
    }

    @Test
    public void shouldDeleteClientFromDatabase(){
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(clientRepository.deleteAppointmentById(1L)).thenReturn(true);
        AppointmentRemoveRequest request = new AppointmentRemoveRequest(1L);
        AppointmentRemoveResponse response = service.execute(request);
        assertFalse(response.hasError());
        assertTrue(response.isAppointmentRemoved());
    }
}