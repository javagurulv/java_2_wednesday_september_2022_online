package lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators;

import lv.javaguru.java2.rentapp.core.database.InMemoryDatabaseImpl;
import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.services.validators.DeleteVehicleByPlateNumberRequestValidator;
import lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators.search_vehicle_fields_validators.SearchVehicleFieldsValidatorMap;
import lv.javaguru.java2.rentapp.enums.VehicleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchVehicleValidatorTest {

    @Mock
    private SearchVehicleFieldsValidatorMap searchVehicleFieldsValidatorMap;
    @Mock
    private SearchVehicleRequestOrderingValidator searchVehicleRequestOrderingValidator;
    @Mock
    private SearchVehicleRequestPagingValidator searchVehicleRequestPagingValidator;
    @Mock
    private SearchVehicleRequest searchVehicleRequest;
    @InjectMocks
    private SearchVehicleValidator searchVehicleValidator;

    @Test
    public void shouldReturnListWithNoErrorsWhenVehicleTypeIsProvidedButOrderingAndPagingIsNullNotProvided() {
        when(searchVehicleRequest.getVehicleType()).thenReturn(VehicleType.PASSENGER_CAR);
        when(searchVehicleRequest.getOrdering()).thenReturn(null);
        when(searchVehicleRequest.getPaging()).thenReturn(null);
        List<CoreError> errors = searchVehicleValidator.validate(searchVehicleRequest);
        assertTrue(errors.isEmpty());
    }

}