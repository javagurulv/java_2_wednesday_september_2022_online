package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.DealDatabase;
import lv.javaguru.java2.rentapp.core.requests.GeneralRentVehicleRequest;
import lv.javaguru.java2.rentapp.core.requests.Paging;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.responses.VehicleAvailabilityResponse;
import lv.javaguru.java2.rentapp.core.services.validators.VehicleAvailabilityServiceValidator;
import lv.javaguru.java2.rentapp.domain.PassengerCar;
import lv.javaguru.java2.rentapp.domain.RentDeal;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import lv.javaguru.java2.rentapp.enums.Colour;
import lv.javaguru.java2.rentapp.enums.EngineType;
import lv.javaguru.java2.rentapp.enums.TransmissionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VehicleAvailabilityServiceTest {

    @Mock
    private DealDatabase dealDatabase;

    @Mock
    private VehicleAvailabilityServiceValidator vehicleAvailabilityServiceValidator;

    @InjectMocks
    private VehicleAvailabilityService vehicleAvailabilityService;

    @Test
    void testShouldReturnResponseWithErrorsIfRequestNotValid() {
        GeneralRentVehicleRequest request = Mockito.mock(GeneralRentVehicleRequest.class);
        Mockito.when(vehicleAvailabilityServiceValidator.validate(request)).thenReturn(List.of(new CoreError("test", "test")));
        VehicleAvailabilityResponse response = vehicleAvailabilityService.execute(request, null);
        assertTrue(response.hasErrors());
        Mockito.verifyNoInteractions(dealDatabase);
    }

    @Test
    void testPassengersCar2IsBusyInrequestedDayCar1And2Avaylable() {
        GeneralRentVehicleRequest request = Mockito.mock(GeneralRentVehicleRequest.class);
        Mockito.when(vehicleAvailabilityServiceValidator.validate(request)).thenReturn(List.of());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Mockito.when(request.getRentStartDate()).thenReturn(LocalDate.now().plusDays(2).format(formatter));
        Mockito.when(request.getRentEndDate()).thenReturn(LocalDate.now().plusDays(5).format(formatter));
        Mockito.when(request.getPaging()).thenReturn(null);

        PassengerCar passengerCar1 = new PassengerCar("a", "a1", true, 1990, Colour.RED, 10.00, EngineType.GAS, "aa1", TransmissionType.MANUAL, 2, 2, 2, true);
        passengerCar1.setId(1L);
        PassengerCar passengerCar2 = new PassengerCar("b", "b1", true, 1991, Colour.RED, 15.00, EngineType.GAS, "bb1", TransmissionType.MANUAL, 2, 2, 2, true);
        passengerCar2.setId(2L);
        PassengerCar passengerCar3 = new PassengerCar("c", "c1", true, 1992, Colour.RED, 20.00, EngineType.GAS, "cc1", TransmissionType.MANUAL, 3, 3, 3, true);
        passengerCar3.setId(3L);
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(passengerCar1);
        vehicles.add(passengerCar2);
        vehicles.add(passengerCar3);

        RentDeal rentDeal = Mockito.mock(RentDeal.class);
        Mockito.when(rentDeal.getVehicle()).thenReturn(passengerCar2);
        Mockito.when(rentDeal.getStartDate()).thenReturn(LocalDate.now().plusDays(2));
        Mockito.when(rentDeal.getEndDate()).thenReturn(LocalDate.now().plusDays(5));
        Mockito.when(dealDatabase.getAllDeals()).thenReturn(List.of(rentDeal));

        VehicleAvailabilityResponse response = vehicleAvailabilityService.execute(request, vehicles);
        assertFalse(response.hasErrors());
        Mockito.verify(dealDatabase).getAllDeals();
        assertEquals(List.of(passengerCar1, passengerCar3), response.getVehicles());
    }

    @Test
    void testPassengersCar2IsBusyInrequestedDayCar1And2Avaylable2() {
        GeneralRentVehicleRequest request = Mockito.mock(GeneralRentVehicleRequest.class);
        Mockito.when(vehicleAvailabilityServiceValidator.validate(request)).thenReturn(List.of());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Mockito.when(request.getRentStartDate()).thenReturn(LocalDate.now().plusDays(1).format(formatter));
        Mockito.when(request.getRentEndDate()).thenReturn(LocalDate.now().plusDays(2).format(formatter));
        Mockito.when(request.getPaging()).thenReturn(null);

        PassengerCar passengerCar1 = new PassengerCar("a", "a1", true, 1990, Colour.RED, 10.00, EngineType.GAS, "aa1", TransmissionType.MANUAL, 2, 2, 2, true);
        passengerCar1.setId(1L);
        PassengerCar passengerCar2 = new PassengerCar("b", "b1", true, 1991, Colour.RED, 15.00, EngineType.GAS, "bb1", TransmissionType.MANUAL, 2, 2, 2, true);
        passengerCar2.setId(2L);
        PassengerCar passengerCar3 = new PassengerCar("c", "c1", true, 1992, Colour.RED, 20.00, EngineType.GAS, "cc1", TransmissionType.MANUAL, 3, 3, 3, true);
        passengerCar3.setId(3L);
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(passengerCar1);
        vehicles.add(passengerCar2);
        vehicles.add(passengerCar3);

        RentDeal rentDeal = Mockito.mock(RentDeal.class);
        Mockito.when(rentDeal.getVehicle()).thenReturn(passengerCar2);
        Mockito.when(rentDeal.getStartDate()).thenReturn(LocalDate.now().plusDays(2));
        Mockito.when(rentDeal.getEndDate()).thenReturn(LocalDate.now().plusDays(5));
        Mockito.when(dealDatabase.getAllDeals()).thenReturn(List.of(rentDeal));

        VehicleAvailabilityResponse response = vehicleAvailabilityService.execute(request, vehicles);
        assertFalse(response.hasErrors());
        Mockito.verify(dealDatabase).getAllDeals();
        assertEquals(List.of(passengerCar1, passengerCar3), response.getVehicles());
    }

    @Test
    void testPassengersCar2IsBusyInrequestedDayCar1And2Avaylable3() {
        GeneralRentVehicleRequest request = Mockito.mock(GeneralRentVehicleRequest.class);
        Mockito.when(vehicleAvailabilityServiceValidator.validate(request)).thenReturn(List.of());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Mockito.when(request.getRentStartDate()).thenReturn(LocalDate.now().plusDays(1).format(formatter));
        Mockito.when(request.getRentEndDate()).thenReturn(LocalDate.now().plusDays(3).format(formatter));
        Mockito.when(request.getPaging()).thenReturn(null);

        PassengerCar passengerCar1 = new PassengerCar("a", "a1", true, 1990, Colour.RED, 10.00, EngineType.GAS, "aa1", TransmissionType.MANUAL, 2, 2, 2, true);
        passengerCar1.setId(1L);
        PassengerCar passengerCar2 = new PassengerCar("b", "b1", true, 1991, Colour.RED, 15.00, EngineType.GAS, "bb1", TransmissionType.MANUAL, 2, 2, 2, true);
        passengerCar2.setId(2L);
        PassengerCar passengerCar3 = new PassengerCar("c", "c1", true, 1992, Colour.RED, 20.00, EngineType.GAS, "cc1", TransmissionType.MANUAL, 3, 3, 3, true);
        passengerCar3.setId(3L);
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(passengerCar1);
        vehicles.add(passengerCar2);
        vehicles.add(passengerCar3);

        RentDeal rentDeal = Mockito.mock(RentDeal.class);
        Mockito.when(rentDeal.getVehicle()).thenReturn(passengerCar2);
        Mockito.when(rentDeal.getStartDate()).thenReturn(LocalDate.now().plusDays(2));
        Mockito.when(rentDeal.getEndDate()).thenReturn(LocalDate.now().plusDays(5));
        Mockito.when(dealDatabase.getAllDeals()).thenReturn(List.of(rentDeal));

        VehicleAvailabilityResponse response = vehicleAvailabilityService.execute(request, vehicles);
        assertFalse(response.hasErrors());
        Mockito.verify(dealDatabase).getAllDeals();
        assertEquals(List.of(passengerCar1, passengerCar3), response.getVehicles());
    }

    @Test
    void testPassengersCar2IsBusyInrequestedDayCar1And2Avaylable4() {
        GeneralRentVehicleRequest request = Mockito.mock(GeneralRentVehicleRequest.class);
        Mockito.when(vehicleAvailabilityServiceValidator.validate(request)).thenReturn(List.of());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Mockito.when(request.getRentStartDate()).thenReturn(LocalDate.now().plusDays(3).format(formatter));
        Mockito.when(request.getRentEndDate()).thenReturn(LocalDate.now().plusDays(5).format(formatter));
        Mockito.when(request.getPaging()).thenReturn(null);

        PassengerCar passengerCar1 = new PassengerCar("a", "a1", true, 1990, Colour.RED, 10.00, EngineType.GAS, "aa1", TransmissionType.MANUAL, 2, 2, 2, true);
        passengerCar1.setId(1L);
        PassengerCar passengerCar2 = new PassengerCar("b", "b1", true, 1991, Colour.RED, 15.00, EngineType.GAS, "bb1", TransmissionType.MANUAL, 2, 2, 2, true);
        passengerCar2.setId(2L);
        PassengerCar passengerCar3 = new PassengerCar("c", "c1", true, 1992, Colour.RED, 20.00, EngineType.GAS, "cc1", TransmissionType.MANUAL, 3, 3, 3, true);
        passengerCar3.setId(3L);
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(passengerCar1);
        vehicles.add(passengerCar2);
        vehicles.add(passengerCar3);

        RentDeal rentDeal = Mockito.mock(RentDeal.class);
        Mockito.when(rentDeal.getVehicle()).thenReturn(passengerCar2);
        Mockito.when(rentDeal.getStartDate()).thenReturn(LocalDate.now().plusDays(2));
        Mockito.when(rentDeal.getEndDate()).thenReturn(LocalDate.now().plusDays(5));
        Mockito.when(dealDatabase.getAllDeals()).thenReturn(List.of(rentDeal));

        VehicleAvailabilityResponse response = vehicleAvailabilityService.execute(request, vehicles);
        assertFalse(response.hasErrors());
        Mockito.verify(dealDatabase).getAllDeals();
        assertEquals(List.of(passengerCar1, passengerCar3), response.getVehicles());
    }

    @Test
    void testPassengersCar2IsBusyInrequestedDayCar1And2Avaylable5() {
        GeneralRentVehicleRequest request = Mockito.mock(GeneralRentVehicleRequest.class);
        Mockito.when(vehicleAvailabilityServiceValidator.validate(request)).thenReturn(List.of());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Mockito.when(request.getRentStartDate()).thenReturn(LocalDate.now().plusDays(3).format(formatter));
        Mockito.when(request.getRentEndDate()).thenReturn(LocalDate.now().plusDays(7).format(formatter));
        Mockito.when(request.getPaging()).thenReturn(null);

        PassengerCar passengerCar1 = new PassengerCar("a", "a1", true, 1990, Colour.RED, 10.00, EngineType.GAS, "aa1", TransmissionType.MANUAL, 2, 2, 2, true);
        passengerCar1.setId(1L);
        PassengerCar passengerCar2 = new PassengerCar("b", "b1", true, 1991, Colour.RED, 15.00, EngineType.GAS, "bb1", TransmissionType.MANUAL, 2, 2, 2, true);
        passengerCar2.setId(2L);
        PassengerCar passengerCar3 = new PassengerCar("c", "c1", true, 1992, Colour.RED, 20.00, EngineType.GAS, "cc1", TransmissionType.MANUAL, 3, 3, 3, true);
        passengerCar3.setId(3L);
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(passengerCar1);
        vehicles.add(passengerCar2);
        vehicles.add(passengerCar3);

        RentDeal rentDeal = Mockito.mock(RentDeal.class);
        Mockito.when(rentDeal.getVehicle()).thenReturn(passengerCar2);
        Mockito.when(rentDeal.getStartDate()).thenReturn(LocalDate.now().plusDays(2));
        Mockito.when(rentDeal.getEndDate()).thenReturn(LocalDate.now().plusDays(5));
        Mockito.when(dealDatabase.getAllDeals()).thenReturn(List.of(rentDeal));

        VehicleAvailabilityResponse response = vehicleAvailabilityService.execute(request, vehicles);
        assertFalse(response.hasErrors());
        Mockito.verify(dealDatabase).getAllDeals();
        assertEquals(List.of(passengerCar1, passengerCar3), response.getVehicles());
    }

    @Test
    void testPassengersCar2IsBusyInrequestedDayCar1And2Avaylable6() {
        GeneralRentVehicleRequest request = Mockito.mock(GeneralRentVehicleRequest.class);
        Mockito.when(vehicleAvailabilityServiceValidator.validate(request)).thenReturn(List.of());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Mockito.when(request.getRentStartDate()).thenReturn(LocalDate.now().plusDays(5).format(formatter));
        Mockito.when(request.getRentEndDate()).thenReturn(LocalDate.now().plusDays(7).format(formatter));
        Mockito.when(request.getPaging()).thenReturn(null);

        PassengerCar passengerCar1 = new PassengerCar("a", "a1", true, 1990, Colour.RED, 10.00, EngineType.GAS, "aa1", TransmissionType.MANUAL, 2, 2, 2, true);
        passengerCar1.setId(1L);
        PassengerCar passengerCar2 = new PassengerCar("b", "b1", true, 1991, Colour.RED, 15.00, EngineType.GAS, "bb1", TransmissionType.MANUAL, 2, 2, 2, true);
        passengerCar2.setId(2L);
        PassengerCar passengerCar3 = new PassengerCar("c", "c1", true, 1992, Colour.RED, 20.00, EngineType.GAS, "cc1", TransmissionType.MANUAL, 3, 3, 3, true);
        passengerCar3.setId(3L);
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(passengerCar1);
        vehicles.add(passengerCar2);
        vehicles.add(passengerCar3);

        RentDeal rentDeal = Mockito.mock(RentDeal.class);
        Mockito.when(rentDeal.getVehicle()).thenReturn(passengerCar3);
        Mockito.when(rentDeal.getStartDate()).thenReturn(LocalDate.now().plusDays(2));
        Mockito.when(rentDeal.getEndDate()).thenReturn(LocalDate.now().plusDays(5));
        Mockito.when(dealDatabase.getAllDeals()).thenReturn(List.of(rentDeal));

        VehicleAvailabilityResponse response = vehicleAvailabilityService.execute(request, vehicles);
        assertFalse(response.hasErrors());
        Mockito.verify(dealDatabase).getAllDeals();
        assertEquals(List.of(passengerCar1, passengerCar2), response.getVehicles());
    }

    @Test
    void testAllCarsNeedToBeAvailableInRequestedDays() {
        GeneralRentVehicleRequest request = Mockito.mock(GeneralRentVehicleRequest.class);
        Mockito.when(vehicleAvailabilityServiceValidator.validate(request)).thenReturn(List.of());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Mockito.when(request.getRentStartDate()).thenReturn(LocalDate.now().plusDays(7).format(formatter));
        Mockito.when(request.getRentEndDate()).thenReturn(LocalDate.now().plusDays(9).format(formatter));

        PassengerCar passengerCar1 = new PassengerCar("a", "a1", true, 1990, Colour.RED, 10.00, EngineType.GAS, "aa1", TransmissionType.MANUAL, 2, 2, 2, true);
        passengerCar1.setId(1L);
        PassengerCar passengerCar2 = new PassengerCar("b", "b1", true, 1991, Colour.RED, 15.00, EngineType.GAS, "bb1", TransmissionType.MANUAL, 2, 2, 2, true);
        passengerCar2.setId(2L);
        PassengerCar passengerCar3 = new PassengerCar("c", "c1", true, 1992, Colour.RED, 20.00, EngineType.GAS, "cc1", TransmissionType.MANUAL, 3, 3, 3, true);
        passengerCar3.setId(3L);
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(passengerCar1);
        vehicles.add(passengerCar2);
        vehicles.add(passengerCar3);

        RentDeal rentDeal = Mockito.mock(RentDeal.class);
//        Mockito.when(rentDeal.getVehicle()).thenReturn(passengerCar2);
        Mockito.when(rentDeal.getStartDate()).thenReturn(LocalDate.now().plusDays(2));
        Mockito.when(rentDeal.getEndDate()).thenReturn(LocalDate.now().plusDays(5));
        Mockito.when(dealDatabase.getAllDeals()).thenReturn(List.of(rentDeal));

        VehicleAvailabilityResponse response = vehicleAvailabilityService.execute(request, vehicles);
        assertFalse(response.hasErrors());
        Mockito.verify(dealDatabase).getAllDeals();
        assertTrue(vehicles.containsAll(response.getVehicles()));

    }
}