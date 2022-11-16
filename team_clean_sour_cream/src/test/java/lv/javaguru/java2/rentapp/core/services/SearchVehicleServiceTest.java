package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.VehicleDatabase;
import lv.javaguru.java2.rentapp.core.requests.Ordering;
import lv.javaguru.java2.rentapp.core.requests.Paging;
import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.responses.SearchVehicleResponse;
import lv.javaguru.java2.rentapp.core.services.search_criterias.*;
import lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators.SearchVehicleValidator;
import lv.javaguru.java2.rentapp.domain.PassengerCar;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import lv.javaguru.java2.rentapp.enums.Colour;
import lv.javaguru.java2.rentapp.enums.EngineType;
import lv.javaguru.java2.rentapp.enums.TransmissionType;
import lv.javaguru.java2.rentapp.enums.VehicleType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class SearchVehicleServiceTest {

    @Mock
    private VehicleDatabase vehicleDatabase;
    @Mock
    private SearchVehicleValidator validator;

    @InjectMocks
    private SearchVehicleService service;

    @Captor
    ArgumentCaptor<SearchCriteria> searchCriteriaCaptor;

    @Test
    void testShouldReturnErrorWhenRequestIsNotValid() {
        SearchVehicleRequest request = SearchVehicleRequest.builder().build();
        CoreError error = new CoreError("test", "test");
        Mockito.when(validator.validate(request)).thenReturn(List.of(error));
        SearchVehicleResponse response = service.execute(request);
        assertTrue(response.hasErrors());
    }

    @Test
    void testCreatedSearchCriteriaIsCorrect() {
        SearchVehicleRequest request = SearchVehicleRequest.builder().vehicleType(VehicleType.PASSENGER_CAR)
                .passengerAmount(3)
                .doorsAmount(3)
                .build();
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        SearchVehicleResponse response = service.execute(request);
        assertFalse(response.hasErrors());

        Mockito.verify(vehicleDatabase).search(searchCriteriaCaptor.capture());
        AndSearchCriteria andSearchCriteria1 = new AndSearchCriteria(new VehicleTypeCriteria("Passenger Car"), null);
        AndSearchCriteria andSearchCriteria2 = new AndSearchCriteria(andSearchCriteria1, new DoorsAmountCriteria(3));
        SearchCriteria expected = new AndSearchCriteria(andSearchCriteria2, new PassengerAmountCriteria(3));
        SearchCriteria actual = searchCriteriaCaptor.getValue();
        assertEquals(expected, actual);
    }

    @Test
    void testShouldReturnUnorderedListOfVehicleWhenOrderingIsNull() {
        Vehicle vehicle1 = getVehicle(2010);
        Vehicle vehicle2 = getVehicle(2000);
        Vehicle vehicle3 = getVehicle(2020);
        SearchVehicleRequest request = SearchVehicleRequest.builder().vehicleType(VehicleType.PASSENGER_CAR).build();
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        Mockito.when(vehicleDatabase.search(any())).thenReturn(List.of(vehicle1, vehicle2, vehicle3));
        SearchVehicleResponse response = service.execute(request);
        assertFalse(response.hasErrors());

        assertEquals(3, response.getVehicleList().size());
        assertEquals(vehicle1, response.getVehicleList().get(0));
        assertEquals(vehicle2, response.getVehicleList().get(1));
        assertEquals(vehicle3, response.getVehicleList().get(2));
    }

    @Test
    void testShouldReturnListOfVehicleOrderedByYearASC() {
        Vehicle vehicle1 = getVehicle(2010);
        Vehicle vehicle2 = getVehicle(2000);
        Vehicle vehicle3 = getVehicle(2020);
        SearchVehicleRequest request = SearchVehicleRequest.builder()
                .vehicleType(VehicleType.PASSENGER_CAR)
                .ordering(new Ordering("year", "ASC")).build();
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        Mockito.when(vehicleDatabase.search(any())).thenReturn(List.of(vehicle1, vehicle2, vehicle3));
        SearchVehicleResponse response = service.execute(request);
        assertFalse(response.hasErrors());

        assertEquals(3, response.getVehicleList().size());
        assertEquals(vehicle2, response.getVehicleList().get(0));
        assertEquals(vehicle1, response.getVehicleList().get(1));
        assertEquals(vehicle3, response.getVehicleList().get(2));
    }

    @Test
    void testShouldReturnListOfVehicleOrderedByYearDESC() {
        Vehicle vehicle1 = getVehicle(2010);
        Vehicle vehicle2 = getVehicle(2000);
        Vehicle vehicle3 = getVehicle(2020);
        SearchVehicleRequest request = SearchVehicleRequest.builder()
                .vehicleType(VehicleType.PASSENGER_CAR)
                .ordering(new Ordering("year", "DESC")).build();
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        Mockito.when(vehicleDatabase.search(any())).thenReturn(List.of(vehicle1, vehicle2, vehicle3));
        SearchVehicleResponse response = service.execute(request);
        assertFalse(response.hasErrors());

        assertEquals(3, response.getVehicleList().size());
        assertEquals(vehicle3, response.getVehicleList().get(0));
        assertEquals(vehicle1, response.getVehicleList().get(1));
        assertEquals(vehicle2, response.getVehicleList().get(2));
    }

    @Test
    void testShouldReturnListOfVehicleOrderedByPriceASC() {
        Vehicle vehicle1 = getVehicle(200.0);
        Vehicle vehicle2 = getVehicle(300.0);
        Vehicle vehicle3 = getVehicle(100.0);
        SearchVehicleRequest request = SearchVehicleRequest.builder()
                .vehicleType(VehicleType.PASSENGER_CAR)
                .ordering(new Ordering("price", "ASC")).build();
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        Mockito.when(vehicleDatabase.search(any())).thenReturn(List.of(vehicle1, vehicle2, vehicle3));
        SearchVehicleResponse response = service.execute(request);
        assertFalse(response.hasErrors());

        assertEquals(3, response.getVehicleList().size());
        assertEquals(vehicle3, response.getVehicleList().get(0));
        assertEquals(vehicle1, response.getVehicleList().get(1));
        assertEquals(vehicle2, response.getVehicleList().get(2));
    }

    @Test
    void testShouldReturnListOfVehicleOrderedByPriceDESC() {
        Vehicle vehicle1 = getVehicle(200.0);
        Vehicle vehicle2 = getVehicle(300.0);
        Vehicle vehicle3 = getVehicle(100.0);
        SearchVehicleRequest request = SearchVehicleRequest.builder()
                .vehicleType(VehicleType.PASSENGER_CAR)
                .ordering(new Ordering("price", "DESC")).build();
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        Mockito.when(vehicleDatabase.search(any())).thenReturn(List.of(vehicle1, vehicle2, vehicle3));
        SearchVehicleResponse response = service.execute(request);
        assertFalse(response.hasErrors());

        assertEquals(3, response.getVehicleList().size());
        assertEquals(vehicle2, response.getVehicleList().get(0));
        assertEquals(vehicle1, response.getVehicleList().get(1));
        assertEquals(vehicle3, response.getVehicleList().get(2));
    }

    @Test
    void testShouldReturnFirstPageOfTheResultUnordered() {
        Vehicle vehicle1 = getVehicle(200.0);
        Vehicle vehicle2 = getVehicle(300.0);
        Vehicle vehicle3 = getVehicle(100.0);
        SearchVehicleRequest request = SearchVehicleRequest.builder()
                .vehicleType(VehicleType.PASSENGER_CAR)
                .paging(new Paging(1, 1)).build();
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        Mockito.when(vehicleDatabase.search(any())).thenReturn(List.of(vehicle1, vehicle2, vehicle3));
        SearchVehicleResponse response = service.execute(request);
        assertFalse(response.hasErrors());

        assertEquals(1, response.getVehicleList().size());
        assertEquals(vehicle1, response.getVehicleList().get(0));
    }

    @Test
    void testShouldReturnSecondPageOfTheResultUnordered() {
        Vehicle vehicle1 = getVehicle(200.0);
        Vehicle vehicle2 = getVehicle(300.0);
        Vehicle vehicle3 = getVehicle(100.0);
        SearchVehicleRequest request = SearchVehicleRequest.builder()
                .vehicleType(VehicleType.PASSENGER_CAR)
                .paging(new Paging(2, 1)).build();
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        Mockito.when(vehicleDatabase.search(any())).thenReturn(List.of(vehicle1, vehicle2, vehicle3));
        SearchVehicleResponse response = service.execute(request);
        assertFalse(response.hasErrors());

        assertEquals(1, response.getVehicleList().size());
        assertEquals(vehicle2, response.getVehicleList().get(0));
    }

    @Test
    void testShouldReturnSecondPageOfTheResultOrderedByPriceASC() {
        Vehicle vehicle1 = getVehicle(200.0);
        Vehicle vehicle2 = getVehicle(300.0);
        Vehicle vehicle3 = getVehicle(100.0);
        SearchVehicleRequest request = SearchVehicleRequest.builder()
                .vehicleType(VehicleType.PASSENGER_CAR)
                .ordering(new Ordering("price", "ASC"))
                .paging(new Paging(2, 1)).build();
        Mockito.when(validator.validate(request)).thenReturn(List.of());
        Mockito.when(vehicleDatabase.search(any())).thenReturn(List.of(vehicle1, vehicle2, vehicle3));
        SearchVehicleResponse response = service.execute(request);
        assertFalse(response.hasErrors());

        assertEquals(1, response.getVehicleList().size());
        assertEquals(vehicle1, response.getVehicleList().get(0));
    }

    private Vehicle getVehicle(Integer year) {
        return new PassengerCar("test", "test", true, year,
                Colour.RED, 100.0, EngineType.GAS, "test", TransmissionType.MANUAL,
                3, 3, 3, true);
    }

    private Vehicle getVehicle(Double price) {
        return new PassengerCar("test", "test", true, 2000,
                Colour.RED, price, EngineType.GAS, "test", TransmissionType.MANUAL,
                3, 3, 3, true);
    }
}