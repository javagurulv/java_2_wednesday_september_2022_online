package lv.javaguru.java2.rentapp.core.database.vehicles_saver;

import lv.javaguru.java2.rentapp.domain.PassengerCar;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Component
public class PassengerCarSaver extends VehicleSaver {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public Long save(Vehicle vehicle) {
        Long vehicleId = saveCommonFields(vehicle);
        PassengerCar passengerCar = (PassengerCar) vehicle;
        SimpleJdbcInsert insertIntoPassengerCar = new SimpleJdbcInsert(jdbcTemplate).withTableName("passenger_cars").usingGeneratedKeyColumns("id");
        Map<String, Object> passengerCarArgs = new HashMap<>();
        passengerCarArgs.put("vehicle_id", vehicleId);
        passengerCarArgs.put("vehicle_type", passengerCar.getVehicleType().name());
        passengerCarArgs.put("passenger_amount", passengerCar.getPassengerAmount());
        passengerCarArgs.put("baggageAmount", passengerCar.getBaggageAmount());
        passengerCarArgs.put("doorsAmount", passengerCar.getDoorsAmount());
        passengerCarArgs.put("air_conditioning", passengerCar.isAirConditioningAvailable());
        insertIntoPassengerCar.execute(passengerCarArgs);
        return vehicleId;
    }
}