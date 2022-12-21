package lv.javaguru.java2.eBooking.core.services.appointment;

import lv.javaguru.java2.eBooking.core.database.ClientRepository;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.AppointmentAddRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.responses.appointment.AppointmentAddResponse;
import lv.javaguru.java2.eBooking.core.services.validators.AppointmentAddValidator;
import lv.javaguru.java2.eBooking.core.services.validators.AppointmentValidationResult;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AppointmentAddServiceTest {

    @Mock
    private ClientRepository clientRepository;
    @Mock
    private AppointmentAddValidator validator;
    @InjectMocks
    private AppointmentAddService service;

    @Test
    public void shouldReturnErrorWhenValidationFails() {
        AppointmentAddRequest request = new AppointmentAddRequest(null, "pedicure");
        Mockito
                .when(validator.validate(request))
                .thenReturn(List.of(new CoreError("Master name",
                        AppointmentValidationResult.MASTERNAME_MUST_NOT_BE_EMPTY)));
        AppointmentAddResponse response = service.execute(request);

        assertTrue(response.hasError());
        assertEquals(response.getErrors().size(), 1);
    }

    @Test
    public void shouldNotInvokeDatabaseWhenValidatorFails() {
        AppointmentAddRequest request = new AppointmentAddRequest(null, null);

        Mockito
                .when(validator.validate(request)).thenReturn(List.of(
                        new CoreError("Master name",
                                AppointmentValidationResult.MASTERNAME_MUST_NOT_BE_EMPTY),
                        new CoreError("Type of service",
                                AppointmentValidationResult.SERVICETYPE_MUST_NOT_BE_EMPTY)));
        AppointmentAddResponse response = service.execute(request);
        assertEquals(response.getErrors().size(), 2);
        Mockito.verifyNoInteractions(clientRepository);
    }

    @Test
    public void shouldReturnResponseWithoutErrorWhenRequestIsValid() {
        AppointmentAddRequest request = new AppointmentAddRequest("Master name", "Type of service");
        Mockito
                .when(validator.validate(request))
                .thenReturn(List.of());
        AppointmentAddResponse response = service.execute(request);
        assertFalse(response.hasError());
    }

    @Test
    public void shouldReturnResponseWithAnAppointmentWhenRequestIsValid() {
        AppointmentAddRequest request = new AppointmentAddRequest("Master name", "Type of service");
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        AppointmentAddResponse response = service.execute(request);
        assertNotNull(response.getNewAppointment());
        assertEquals(response.getNewAppointment().getMasterName(), request.getMasterName());
        assertEquals(response.getNewAppointment().getTypeOfService(), request.getTypeOfService());
    }
}