package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.AddVehicleResponse;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.services.new_vehicle_creators.VehicleCreator;
import lv.javaguru.java2.rentapp.core.services.new_vehicle_creators.VehicleCreatorMap;
import lv.javaguru.java2.rentapp.core.services.validators.add_vehicle_validators.AddPassengerCarValidator;
import lv.javaguru.java2.rentapp.core.services.validators.add_vehicle_validators.AddVehicleValidatorMap;
import lv.javaguru.java2.rentapp.domain.PassengerCar;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class AddVehicleServiceTest {

    @Mock private Database database;
    @Mock private VehicleCreatorMap vehicleCreatorMap;
    @Mock private VehicleCreator vehicleCreator;
    @Mock private AddVehicleValidatorMap addVehicleValidatorMap;
    @Mock private AddPassengerCarValidator addPassengerCarValidator;

    @InjectMocks
    private AddVehicleService service;

    @Test
    void testReturnErrorWhenRequestIsNotValid() {
        AddVehicleRequest request = Mockito.mock(AddVehicleRequest.class);
        CoreError error = new CoreError("test", "test");
        Mockito.when(addVehicleValidatorMap.getVehicleValidatorByCarType(any())).thenReturn(addPassengerCarValidator);
        Mockito.when(addPassengerCarValidator.validate(request)).thenReturn(List.of(error));
        AddVehicleResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("test", response.getErrors().get(0).getField());
        assertEquals("test", response.getErrors().get(0).getMessage());
    }

    @Test
    void testServiceReturnsAddedVehicleWhenRequestIsValid() {
        AddVehicleRequest request = Mockito.mock(AddVehicleRequest.class);
        PassengerCar addedVehicle = Mockito.mock(PassengerCar.class);
        Mockito.when(addVehicleValidatorMap.getVehicleValidatorByCarType(any())).thenReturn(addPassengerCarValidator);
        Mockito.when(addPassengerCarValidator.validate(request)).thenReturn(List.of());
        Mockito.when(vehicleCreatorMap.getVehicleTypeCreatorByCarType(any())).thenReturn(vehicleCreator);
        Mockito.when(vehicleCreator.createVehicle(request)).thenReturn(addedVehicle);

        AddVehicleResponse response = service.execute(request);

        Mockito.verify(database).addNewVehicle(addedVehicle);
        assertFalse(response.hasErrors());
        assertEquals(addedVehicle, response.getNewVehicle());
    }


}