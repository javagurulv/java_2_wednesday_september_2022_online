package lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators.search_vehicle_fields_validators;

import lv.javaguru.java2.rentapp.enums.VehicleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchVehicleFieldsValidatorMapTest {

    SearchVehicleFieldsValidatorMap searchVehicleFieldsValidatorMap;

    SearchPassengerCarValidator searchPassengerCarValidator;

    SearchMiniBusValidator searchMiniBusValidator;

    SearchMotorcycleValidator searchMotorcycleValidator;

    SearchCarTrailerValidator searchCarTrailerValidator;

    @BeforeEach
    void setUp() {
        searchPassengerCarValidator = new SearchPassengerCarValidator();
        searchMiniBusValidator = new SearchMiniBusValidator();
        searchMotorcycleValidator = new SearchMotorcycleValidator();
        searchCarTrailerValidator = new SearchCarTrailerValidator();
        searchVehicleFieldsValidatorMap = new SearchVehicleFieldsValidatorMap(searchPassengerCarValidator, searchMiniBusValidator,
                searchMotorcycleValidator, searchCarTrailerValidator);
    }

    @Test
    public void shouldReturnSearchPassengerCarValidatorWhenVehicleTypeIsPassengerCar() {
        SearchVehicleFieldsValidator expectedValidator = searchVehicleFieldsValidatorMap.getVehicleValidatorByCarType(VehicleType.PASSENGER_CAR);
        assertEquals(expectedValidator.getClass(), SearchPassengerCarValidator.class);
    }

    @Test
    public void shouldReturnSearchMiniBusValidatorWhenVehicleTypeIsMiniBuss() {
        SearchVehicleFieldsValidator expectedValidator = searchVehicleFieldsValidatorMap.getVehicleValidatorByCarType(VehicleType.MINIBUS);
        assertEquals(expectedValidator.getClass(), SearchMiniBusValidator.class);
    }

    @Test
    public void shouldReturnSearchMotorcycleValidatorWhenVehicleTypeIsMotorcycle() {
        SearchVehicleFieldsValidator expectedValidator = searchVehicleFieldsValidatorMap.getVehicleValidatorByCarType(VehicleType.MOTORCYCLE);
        assertEquals(expectedValidator.getClass(), SearchMotorcycleValidator.class);
    }

    @Test
    public void shouldReturnSearchCarTrailerValidatorWhenVehicleTypeIsCarTrailer() {
        SearchVehicleFieldsValidator expectedValidator = searchVehicleFieldsValidatorMap.getVehicleValidatorByCarType(VehicleType.CAR_TRAILER);
        assertEquals(expectedValidator.getClass(), SearchCarTrailerValidator.class);
    }
}