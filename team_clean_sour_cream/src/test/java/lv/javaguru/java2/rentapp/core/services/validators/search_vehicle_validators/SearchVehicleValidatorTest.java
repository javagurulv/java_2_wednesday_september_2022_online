package lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators;

import lv.javaguru.java2.rentapp.core.requests.Ordering;
import lv.javaguru.java2.rentapp.core.requests.Paging;
import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators.search_vehicle_fields_validators.SearchVehicleFieldsValidator;
import lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators.search_vehicle_fields_validators.SearchVehicleFieldsValidatorMap;
import lv.javaguru.java2.rentapp.enums.VehicleType;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static lv.javaguru.java2.rentapp.domain.PassengerCar.CAR_MIN_DOORS_AMOUNT;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchVehicleValidatorTest {

    @Mock
    private SearchVehicleFieldsValidatorMap validatorMap;
    @Mock
    private SearchVehicleRequestOrderingValidator orderingValidator;
    @Mock
    private SearchVehicleRequestPagingValidator pagingValidator;
    @InjectMocks
    private SearchVehicleValidator validator;

    @Test
    public void shouldReturnListWithNoErrorsWhenVehicleTypeIsProvidedButOrderingAndPagingIsNullNotProvided() {
        SearchVehicleRequest request = mock(SearchVehicleRequest.class);
        when(request.getVehicleType()).thenReturn(VehicleType.PASSENGER_CAR);
        when(request.getOrdering()).thenReturn(null);
        when(request.getPaging()).thenReturn(null);
        SearchVehicleFieldsValidator fieldsValidator = mock(SearchVehicleFieldsValidator.class);
        when(fieldsValidator.validate(request)).thenReturn(List.of());
        when(validatorMap.getVehicleValidatorByCarType(VehicleType.PASSENGER_CAR)).thenReturn(fieldsValidator);
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldNotInvokeOrderingValidator() {
        SearchVehicleRequest request = mock(SearchVehicleRequest.class);
        when(request.getVehicleType()).thenReturn(VehicleType.PASSENGER_CAR);
        when(request.getOrdering()).thenReturn(null);
        when(request.getPaging()).thenReturn(null);
        SearchVehicleFieldsValidator fieldsValidator = mock(SearchVehicleFieldsValidator.class);
        when(fieldsValidator.validate(request)).thenReturn(List.of());
        when(validatorMap.getVehicleValidatorByCarType(VehicleType.PASSENGER_CAR)).thenReturn(fieldsValidator);
        validator.validate(request);
        Mockito.verifyNoInteractions(orderingValidator);
    }

    @Test
    public void shouldNotInvokePagingValidator() {
        SearchVehicleRequest request = mock(SearchVehicleRequest.class);
        when(request.getVehicleType()).thenReturn(VehicleType.PASSENGER_CAR);
        when(request.getOrdering()).thenReturn(null);
        when(request.getPaging()).thenReturn(null);
        SearchVehicleFieldsValidator fieldsValidator = mock(SearchVehicleFieldsValidator.class);
        when(fieldsValidator.validate(request)).thenReturn(List.of());
        when(validatorMap.getVehicleValidatorByCarType(VehicleType.PASSENGER_CAR)).thenReturn(fieldsValidator);
        validator.validate(request);
        Mockito.verifyNoInteractions(pagingValidator);
    }

    @Test
    public void shouldReturnFieldValidatorErrors() {
        SearchVehicleRequest request = mock(SearchVehicleRequest.class);
        when(request.getVehicleType()).thenReturn(VehicleType.PASSENGER_CAR);
        when(request.getOrdering()).thenReturn(null);
        when(request.getPaging()).thenReturn(null);
        SearchVehicleFieldsValidator fieldsValidator = mock(SearchVehicleFieldsValidator.class);
        when(fieldsValidator.validate(request)).thenReturn(List.of(
                new CoreError("Doors amount", "can`t be negative, zero or less than " + CAR_MIN_DOORS_AMOUNT)));
        when(validatorMap.getVehicleValidatorByCarType(VehicleType.PASSENGER_CAR)).thenReturn(fieldsValidator);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Doors amount", errors.get(0).getField());
        assertEquals("can`t be negative, zero or less than " + CAR_MIN_DOORS_AMOUNT, errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnOrderingValidatorErrors() {
        SearchVehicleRequest request = mock(SearchVehicleRequest.class);
        when(request.getVehicleType()).thenReturn(VehicleType.PASSENGER_CAR);
        when(request.getOrdering()).thenReturn(new Ordering(null, "ASC"));
        when(request.getPaging()).thenReturn(null);
        SearchVehicleFieldsValidator fieldsValidator = mock(SearchVehicleFieldsValidator.class);
        when(fieldsValidator.validate(request)).thenReturn(new ArrayList<>());
        when(validatorMap.getVehicleValidatorByCarType(VehicleType.PASSENGER_CAR)).thenReturn(fieldsValidator);
        when(orderingValidator.validate(request.getOrdering())).thenReturn(
                List.of(new CoreError("orderBy", "Must not be empty!"))
        );
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("orderBy", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnPagingValidatorErrors() {
        SearchVehicleRequest request = mock(SearchVehicleRequest.class);
        when(request.getVehicleType()).thenReturn(VehicleType.PASSENGER_CAR);
        when(request.getOrdering()).thenReturn(null);
        when(request.getPaging()).thenReturn(new Paging(null, 1));
        SearchVehicleFieldsValidator fieldsValidator = mock(SearchVehicleFieldsValidator.class);
        when(fieldsValidator.validate(request)).thenReturn(new ArrayList<>());
        when(validatorMap.getVehicleValidatorByCarType(VehicleType.PASSENGER_CAR)).thenReturn(fieldsValidator);
        when(pagingValidator.validate(request.getPaging())).thenReturn(
                List.of(new CoreError("pageNumber", "Must not be empty!"))
        );
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals("pageNumber", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnAllValidatorsErrors() {
        SearchVehicleRequest request = mock(SearchVehicleRequest.class);
        when(request.getVehicleType()).thenReturn(VehicleType.PASSENGER_CAR);
        when(request.getOrdering()).thenReturn(new Ordering(null, "ASC"));
        when(request.getPaging()).thenReturn(new Paging(null, 1));
        SearchVehicleFieldsValidator fieldsValidator = mock(SearchVehicleFieldsValidator.class);
        when(fieldsValidator.validate(request)).thenReturn(new ArrayList<>());
        when(validatorMap.getVehicleValidatorByCarType(VehicleType.PASSENGER_CAR)).thenReturn(fieldsValidator);
        when(pagingValidator.validate(request.getPaging())).thenReturn(
                List.of(new CoreError("pageNumber", "Must not be empty!"))
        );
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals("pageNumber", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

}