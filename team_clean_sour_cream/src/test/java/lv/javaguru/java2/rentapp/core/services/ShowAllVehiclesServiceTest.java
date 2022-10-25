package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.VehicleDatabase;
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

    @Mock private VehicleDatabase vehicleDatabase;

    @InjectMocks
    private ShowAllVehiclesService service;

    @Test
    void testServiceShouldReturnListOfVehicles() {
        Vehicle vehicle1 = Mockito.mock(Vehicle.class);
        Vehicle vehicle2 = Mockito.mock(Vehicle.class);
        Vehicle vehicle3 = Mockito.mock(Vehicle.class);

        Mockito.when(vehicleDatabase.getAllVehicles()).thenReturn(List.of(vehicle1, vehicle2, vehicle3));
        ShowAllVehiclesResponse response = service.execute();
        assertEquals(3, response.getVehicles().size());
    }
}