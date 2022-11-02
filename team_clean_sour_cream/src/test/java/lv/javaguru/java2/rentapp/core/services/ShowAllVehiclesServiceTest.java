package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.VehicleDatabase;
import lv.javaguru.java2.rentapp.core.requests.ShowAllVehiclesRequest;
import lv.javaguru.java2.rentapp.core.responses.ShowAllVehiclesResponse;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ShowAllVehiclesServiceTest {

    @Mock
    private VehicleDatabase vehicleDatabase;

    @InjectMocks
    private ShowAllVehiclesService service;

    @Test
    void testServiceShouldReturnListOfVehiclesWhenListIsNotEmpty() {
        Vehicle vehicle1 = Mockito.mock(Vehicle.class);
        Vehicle vehicle2 = Mockito.mock(Vehicle.class);
        Vehicle vehicle3 = Mockito.mock(Vehicle.class);

        ShowAllVehiclesRequest request = new ShowAllVehiclesRequest();
        Mockito.when(vehicleDatabase.getAllVehicles()).thenReturn(List.of(vehicle1, vehicle2, vehicle3));
        ShowAllVehiclesResponse response = service.execute(request);
        assertEquals(3, response.getVehicles().size());
        assertNull(response.getMsg());
    }

    @Test
    void testServiceShouldReturnMessageWhenListIsEmpty() {
        ShowAllVehiclesRequest request = new ShowAllVehiclesRequest();
        Mockito.when(vehicleDatabase.getAllVehicles()).thenReturn(List.of());
        ShowAllVehiclesResponse response = service.execute(request);
        assertEquals("There is no vehicles in Data Base", response.getMsg());
        assertNull(response.getVehicles());
    }
}