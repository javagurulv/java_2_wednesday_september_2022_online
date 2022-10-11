package lv.javaguru.java2.rentapp.core.services.validators.delete_vehicle_service;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.DeleteVehicleByPlateNumberRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.responses.DeleteVehicleByPlateNumberResponse;
import lv.javaguru.java2.rentapp.core.services.DeleteVehicleByPlateNumberService;
import lv.javaguru.java2.rentapp.core.services.validators.DeleteVehicleByPlateNumberRequestValidator;
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
 class DeleteVehicleServiceTest {

 @Mock private DeleteVehicleByPlateNumberRequestValidator validator;
 @Mock private Database database;


 @InjectMocks
 private DeleteVehicleByPlateNumberService service;

 @Test
 void testShouldReturnResponseWithErrorWhenRequestIsNotValid() {
  DeleteVehicleByPlateNumberRequest request = new DeleteVehicleByPlateNumberRequest("test");
  CoreError error = new CoreError("test" , "test");
  Mockito.when(validator.validate(request)).thenReturn(List.of(error));
  DeleteVehicleByPlateNumberResponse response = service.execute(request);
  assertTrue(response.hasErrors());
 }

 @Test
 void testShouldReturnResponseTrueWhenPlateNumberDeleted() {
  DeleteVehicleByPlateNumberRequest request = new DeleteVehicleByPlateNumberRequest("test");
  Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
  DeleteVehicleByPlateNumberResponse response = service.execute(request);
  assertFalse(response.hasErrors());
  assertEquals("Your vehicle was removed from list.", response.getVehicleDeletedMsg());
  assertNotNull(request.getPlateNumber());
  Mockito.verify(database).deleteVehicleByPlateNumber(request.getPlateNumber());
 }
}
