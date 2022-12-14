package lv.javaguru.java2.eBooking.core.services.appointment.search;

import lv.javaguru.java2.eBooking.core.requests.appointment_request.Ordering;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.Paging;
import lv.javaguru.java2.eBooking.core.requests.appointment_request.AppointmentSearchRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import lv.javaguru.java2.eBooking.core.services.validators.AppointmentValidationResult;

import lv.javaguru.java2.eBooking.core.services.validators.AppointmentSearchValidator;
import org.junit.Test;


import java.util.List;

import static org.junit.Assert.*;

public class AppointmentSearchValidatorTest {

    AppointmentSearchValidator validator = new AppointmentSearchValidator();

    @Test
    public void shouldNotReturnErrorWhenMasterNameIsProvided() {
        AppointmentSearchRequest request = new AppointmentSearchRequest("Master name: ", null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorWhenTypeOfServiceIsProvided() {
        AppointmentSearchRequest request = new AppointmentSearchRequest(null, "Type of service: ");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorWhenMasterNameAndTypeOfServiceAreProvided() {
        AppointmentSearchRequest request = new AppointmentSearchRequest("Master name: ", "Type of service: ");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenMasterNameAndTypeOfServiceAreNotProvided() {
        AppointmentSearchRequest request = new AppointmentSearchRequest(null, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getField(), "Master name: ");
        assertEquals(errors.get(0).getAppointmentValidationMessage(), AppointmentValidationResult.MASTERNAME_MUST_NOT_BE_EMPTY);
        assertEquals(errors.get(1).getField(), "Type of service: ");
        assertEquals(errors.get(1).getAppointmentValidationMessage(), AppointmentValidationResult.SERVICETYPE_MUST_NOT_BE_EMPTY);
    }

    @Test
    public void shouldReturnErrorWhenOrderByIsEmpty() {
        Ordering ordering = new Ordering(null);
        AppointmentSearchRequest request = new AppointmentSearchRequest(
                "Master name: ",
                "Type of service: ",
                ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Order by");
        assertEquals(errors.get(0).getAppointmentValidationMessage(),
                AppointmentValidationResult.APPOINTMENT_ORDERBY_MUST_NOT_BE_EMPTY);
    }

    @Test
    public void shouldReturnErrorWhenOrderByHasNotCorrectValue() {
        Ordering ordering = new Ordering("valueIsNotCorrect");
        AppointmentSearchRequest request = new AppointmentSearchRequest(
                "Master name: ",
                "Type of service: ",
                ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Order by");
        assertEquals(errors.get(0).getAppointmentValidationMessage(),
                AppointmentValidationResult.APPOINTMENT_SHOULD_CONTAIN_MASTERNAME_OR_SERVICETYPE);
    }
    @Test
    public void shouldReturnErrorWhenPageSizeIsEmpty(){
        Paging paging = new Paging(1,null);
        AppointmentSearchRequest request= new AppointmentSearchRequest(
                "Master name: ",
                "Type of service:" ,
                paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),1);
        assertEquals(errors.get(0).getField(),"Page size");
        assertEquals(errors.get(0).getAppointmentValidationMessage(),
                AppointmentValidationResult.APPOINTMENT_PAGESIZE_MUST_NOT_BE_EMPTY);
    }
    @Test
    public void shouldReturnErrorWhenPageSizeEqualsZeroOrLess(){
        Paging paging = new Paging(1,0);
        AppointmentSearchRequest request= new AppointmentSearchRequest(
                "Master name: ",
                "Type of service:" ,
                paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),1);
        assertEquals(errors.get(0).getField(),"Page size");
        assertEquals(errors.get(0).getAppointmentValidationMessage(),
                AppointmentValidationResult.APPOINTMENT_PAGESIZE_MUST_BE_GREATER_THAN_ZERO);
    }

    @Test
    public void shouldReturnErrorWhenPageNumberIsEmpty(){
        Paging paging = new Paging(null,1);
        AppointmentSearchRequest request= new AppointmentSearchRequest(
                "Master name: ",
                "Type of service:" ,
                paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),1);
        assertEquals(errors.get(0).getField(),"Page number");
        assertEquals(errors.get(0).getAppointmentValidationMessage(),
                AppointmentValidationResult.APPOINTMENT_PAGENUMBER_MUST_NOT_BE_EMPTY);
    }

    @Test
    public void shouldReturnErrorWhenPageNumberEqualsZeroOrLess(){
        Paging paging = new Paging(0,1);
        AppointmentSearchRequest request= new AppointmentSearchRequest(
                "Master name: ",
                "Type of service:" ,
                paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(),1);
        assertEquals(errors.get(0).getField(),"Page number");
        assertEquals(errors.get(0).getAppointmentValidationMessage(),
                AppointmentValidationResult.APPOINTMENT_PAGENUMBER_MUST_BE_GREATER_THAN_ZERO);
    }
}