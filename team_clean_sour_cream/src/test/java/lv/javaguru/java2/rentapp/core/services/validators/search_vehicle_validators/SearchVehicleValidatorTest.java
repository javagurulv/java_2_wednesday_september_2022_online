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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchVehicleValidatorTest {

    @Mock private SearchVehicleFieldsValidatorMap validatorMap;
	@Mock private SearchVehicleRequestOrderingValidator orderingValidator;
    @Mock private SearchVehicleRequestPagingValidator pagingValidator;
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
				new CoreError("doorsAmount", "Can not be null!")));
		when(validatorMap.getVehicleValidatorByCarType(VehicleType.PASSENGER_CAR)).thenReturn(fieldsValidator);
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getField(), "doorsAmount");
		assertEquals(errors.get(0).getMessage(), "Can not be null!");
	}

	@Test
	public void shouldReturnOrderingValidatorErrors() {
		SearchVehicleRequest request = mock(SearchVehicleRequest.class);
		when(request.getVehicleType()).thenReturn(VehicleType.PASSENGER_CAR);
		when(request.getOrdering()).thenReturn(new Ordering("field", "ASC"));
		when(request.getPaging()).thenReturn(null);
		SearchVehicleFieldsValidator fieldsValidator = mock(SearchVehicleFieldsValidator.class);
		when(fieldsValidator.validate(request)).thenReturn(new ArrayList<>());
		when(validatorMap.getVehicleValidatorByCarType(VehicleType.PASSENGER_CAR)).thenReturn(fieldsValidator);
		when(orderingValidator.validate(request.getOrdering())).thenReturn(
				List.of(new CoreError("orderBy", "Can not be null"))
		);
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getField(), "orderBy");
		assertEquals(errors.get(0).getMessage(), "Can not be null");
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
				List.of(new CoreError("pageNumber", "Can not be null"))
		);
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getField(), "pageNumber");
		assertEquals(errors.get(0).getMessage(), "Can not be null");
	}

}