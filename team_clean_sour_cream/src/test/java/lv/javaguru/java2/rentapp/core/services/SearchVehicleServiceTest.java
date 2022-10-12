package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.responses.SearchVehicleResponse;
import lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators.SearchVehicleValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SearchVehicleServiceTest {

    @Mock private Database database;
    @Mock private SearchVehicleValidator validator;

    @InjectMocks
    private SearchVehicleService service;

    @Test
    void testShouldReturnErrorWhenRequestIsNotValid() {
        SearchVehicleRequest request = SearchVehicleRequest.builder().build();
        CoreError error = new CoreError("test", "test");
        Mockito.when(validator.validate(request)).thenReturn(List.of(error));
        SearchVehicleResponse response = service.execute(request);
        assertTrue(response.hasErrors());
    }

}