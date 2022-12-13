package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.DealDatabase;
import lv.javaguru.java2.rentapp.core.database.VehicleDatabase;
import lv.javaguru.java2.rentapp.core.requests.GeneralRentVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.responses.RentVehicleResponse;
import lv.javaguru.java2.rentapp.core.services.validators.RentVehicleValidator;
import lv.javaguru.java2.rentapp.domain.PassengerCar;
import lv.javaguru.java2.rentapp.domain.RentDeal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RentVehicleServiceTest {

    @Mock private DealDatabase dealDatabase;
    @Mock private VehicleDatabase vehicleDatabase;
    @Mock private RentVehicleValidator validator;

    @InjectMocks
    private RentVehicleService service;


    @Captor
    ArgumentCaptor<RentDeal> rentDealCaptor;

    @Test
    void testShouldReturnResponseWithErrorsIfRequestNotValid() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        PassengerCar vehicle = Mockito.mock(PassengerCar.class);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().vehicleId(1L).personalId("000000-00000")
                .firstName("test").lastName("test").email("test@test.com").phoneNumber("11656198").availableVehicles(List.of(vehicle))
                .rentStartDate(LocalDate.now().format(formatter)).rentEndDate(LocalDate.now().plusDays(1).format(formatter)).build();
        Mockito.when(validator.validate(request)).thenReturn(List.of(new CoreError("test", "test")));

        RentVehicleResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertNull(response.getMessage());
        assertNull(response.getNewRentDeal());
        Mockito.verifyNoInteractions(vehicleDatabase);
        Mockito.verifyNoInteractions(dealDatabase);
    }

    @Test
    void testShouldReturnResponseWithMessageWhenRequestIsValidAndRentDealIsCreated() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        PassengerCar vehicle = Mockito.mock(PassengerCar.class);
        GeneralRentVehicleRequest request = GeneralRentVehicleRequest.builder().vehicleId(1L).personalId("000000-00000")
                .firstName("test").lastName("test").email("test@test.com").phoneNumber("1561891").availableVehicles(List.of(vehicle))
                .rentStartDate(LocalDate.now().format(formatter)).rentEndDate(LocalDate.now().plusDays(1).format(formatter)).build();
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        Mockito.when(vehicleDatabase.getById(1L)).thenReturn(Optional.of(vehicle));

        RentVehicleResponse response = service.execute(request);

        Mockito.verify(dealDatabase).save(rentDealCaptor.capture(), );

        assertFalse(response.hasErrors());
        assertNull(response.getMessage());
        assertEquals(rentDealCaptor.getValue(), response.getNewRentDeal());
    }


}