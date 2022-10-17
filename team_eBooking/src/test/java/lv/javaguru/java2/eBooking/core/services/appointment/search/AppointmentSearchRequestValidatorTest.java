package lv.javaguru.java2.eBooking.core.services.appointment.search;

import lv.javaguru.java2.eBooking.core.requests.appointment_request.Ordering;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.SearchAppointmentRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.services.appointment.add.AppointmentValidationResult;
import lv.javaguru.java2.eBooking.core.services.client.add.ClientValidationResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AppointmentSearchRequestValidatorTest {

    AppointmentSearchRequestValidator validator = new AppointmentSearchRequestValidator();
    Ordering ordering;
    @Test
    public void shouldNotReturnErrorWhenMasterNameIsProvided() {
        SearchAppointmentRequest request = new SearchAppointmentRequest("Master name: ", null,ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),0);
    }

    @Test
    public void shouldNotReturnErrorWhenTypeOfServiceIsProvided() {
        SearchAppointmentRequest request = new SearchAppointmentRequest(null, "Type of service: ",ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),0);
    }

    @Test
    public void shouldNotReturnErrorWhenMasterNameAndTypeOfServiceAreProvided() {
        SearchAppointmentRequest request = new SearchAppointmentRequest("Master name: ", "Type of service: ",ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),0);
    }

    @Test
    public void shouldReturnErrorWhenMasterNameAndTypeOfServiceAreNotProvided() {
        SearchAppointmentRequest request = new SearchAppointmentRequest(null, null,ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),2);
        assertEquals(errors.get(0).getField(),"Master name: ");
        assertEquals(errors.get(0).getAppointmentValidationMessage(), AppointmentValidationResult.MASTERNAME_MUST_NOT_BE_EMPTY);
        assertEquals(errors.get(1).getField(), "Type of service: ");
        assertEquals(errors.get(1).getAppointmentValidationMessage(),AppointmentValidationResult.SERVICETYPE_MUST_NOT_BE_EMPTY);
    }
}