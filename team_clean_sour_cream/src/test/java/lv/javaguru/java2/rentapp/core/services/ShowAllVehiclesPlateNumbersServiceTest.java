package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.VehicleDatabase;
import lv.javaguru.java2.rentapp.core.requests.ShowAllVehiclesPlateNumbersRequest;
import lv.javaguru.java2.rentapp.core.responses.ShowAllVehiclesPlateNumbersResponse;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class ShowAllVehiclesPlateNumbersServiceTest {

    @Mock
    private VehicleDatabase vehicleDatabase;

    @InjectMocks
    ShowAllVehiclesPlateNumbersService showAllVehiclesPlateNumbersService;

    @Test
    void shouldReturnMessageWhenListOfPlateNumbersIsEmpty() {
        ShowAllVehiclesPlateNumbersRequest request = new ShowAllVehiclesPlateNumbersRequest();
        Mockito.when(vehicleDatabase.getAllVehicles()).thenReturn(List.of());
        ShowAllVehiclesPlateNumbersResponse response = showAllVehiclesPlateNumbersService.execute(request);
        assertEquals("There is no any plate number", response.getMsg());
        assertNull(response.getVehiclesPlateNumbers());
    }

    @Test
    void shouldReturnListOfVehiclePlateNumbersWhenListIsNotEmpty() {
        Vehicle vehicle1 = Mockito.mock(Vehicle.class);
        Vehicle vehicle2 = Mockito.mock(Vehicle.class);

        ShowAllVehiclesPlateNumbersRequest request = new ShowAllVehiclesPlateNumbersRequest();
        Mockito.when(vehicleDatabase.getAllVehicles()).thenReturn(List.of(vehicle1, vehicle2));
        ShowAllVehiclesPlateNumbersResponse response = showAllVehiclesPlateNumbersService.execute(request);
        assertEquals(2, response.getVehiclesPlateNumbers().size());
        assertNull(response.getMsg());
    }
}