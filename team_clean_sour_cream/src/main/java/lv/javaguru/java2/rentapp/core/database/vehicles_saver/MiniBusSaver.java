package lv.javaguru.java2.rentapp.core.database.vehicles_saver;

import lv.javaguru.java2.rentapp.domain.MiniBus;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Component
public class MiniBusSaver extends VehicleSaver {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public Long save(Vehicle vehicle) {
        Long vehicleId = saveCommonFields(vehicle);
        MiniBus miniBus = (MiniBus) vehicle;
        SimpleJdbcInsert insertIntoMiniBus = new SimpleJdbcInsert(jdbcTemplate).withTableName("mini_buses").usingGeneratedKeyColumns("id");
        Map<String, Object> miniBusArgs = new HashMap<>();
        miniBusArgs.put("vehicle_id", vehicleId);
        miniBusArgs.put("vehicle_type", miniBus.getVehicleType().name());
        miniBusArgs.put("passenger_amount", miniBus.getPassengerAmount());
        miniBusArgs.put("baggageAmount", miniBus.getBaggageAmount());
        miniBusArgs.put("doorsAmount", miniBus.getDoorsAmount());
        miniBusArgs.put("air_conditioning", miniBus.isAirConditioningAvailable());
        insertIntoMiniBus.execute(miniBusArgs);
        return vehicleId;
    }
}