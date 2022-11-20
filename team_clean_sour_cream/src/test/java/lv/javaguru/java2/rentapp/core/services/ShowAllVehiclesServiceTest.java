package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.VehicleDatabase;
import lv.javaguru.java2.rentapp.core.requests.Paging;
import lv.javaguru.java2.rentapp.core.requests.ShowAllVehiclesRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.responses.ShowAllVehiclesResponse;
import lv.javaguru.java2.rentapp.core.services.validators.PagingValidator;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ShowAllVehiclesServiceTest {

    @Mock
    private VehicleDatabase vehicleDatabase;
    @Mock
    private PagingValidator pagingValidator;

    @InjectMocks
    private ShowAllVehiclesService service;

    @Test
    void testServiceShouldReturnListOfVehiclesWhenListIsNotEmptyAndPagingNotProvided() {
        Vehicle vehicle1 = Mockito.mock(Vehicle.class);
        Vehicle vehicle2 = Mockito.mock(Vehicle.class);
        Vehicle vehicle3 = Mockito.mock(Vehicle.class);

        ShowAllVehiclesRequest request = ShowAllVehiclesRequest.builder().build();
        Mockito.when(vehicleDatabase.getAllVehicles()).thenReturn(List.of(vehicle1, vehicle2, vehicle3));
        ShowAllVehiclesResponse response = service.execute(request);
        assertEquals(3, response.getVehicles().size());
        assertNull(response.getMsg());
        Mockito.verifyNoInteractions(pagingValidator);
    }

    @Test
    void testServiceShouldReturnMessageWhenListIsEmpty() {
        ShowAllVehiclesRequest request = ShowAllVehiclesRequest.builder().build();
        Mockito.when(vehicleDatabase.getAllVehicles()).thenReturn(List.of());
        ShowAllVehiclesResponse response = service.execute(request);
        assertEquals("There is no vehicles in Data Base", response.getMsg());
        assertNull(response.getVehicles());
    }

    @Test
    void testServiceCallsValidatorAndReturnsNoErrorWhenValidPagingIsProvided() {
        ShowAllVehiclesRequest request = ShowAllVehiclesRequest.builder().paging(new Paging(1, 1)).build();
        Mockito.when(pagingValidator.validate(request.getPaging())).thenReturn(new ArrayList<>());
        Mockito.when(vehicleDatabase.getAllVehicles()).thenReturn(new ArrayList<>());
        ShowAllVehiclesResponse response = service.execute(request);
        assertEquals("There is no vehicles in Data Base", response.getMsg());
        assertNull(response.getVehicles());
        Mockito.verify(pagingValidator).validate(request.getPaging());
    }

    @Test
    void testServiceCallsValidatorAndReturnsErrorWhenInvalidPagingIsProvided() {
        ShowAllVehiclesRequest request = ShowAllVehiclesRequest.builder().paging(new Paging(1, null)).build();
        Mockito.when(pagingValidator.validate(request.getPaging())).thenReturn(List.of(new CoreError("test", "test")));

        ShowAllVehiclesResponse actualResponse = service.execute(request);

        assertEquals(1, actualResponse.getErrors().size());
        assertNull(actualResponse.getVehicles());
        Mockito.verifyNoInteractions(vehicleDatabase);
    }

}