package lv.javaguru.java2.rentapp.core.database.vehicles_saver;

import lv.javaguru.java2.rentapp.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.HashMap;
import java.util.Map;


@Component
public abstract class VehicleSaver {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public abstract Long save(Vehicle vehicle);

    protected Long saveCommonFields(Vehicle vehicle) {
        SimpleJdbcInsert insertIntoVehicles = new SimpleJdbcInsert(jdbcTemplate).withTableName("vehicles").usingGeneratedKeyColumns("id");
        Map<String, Object> vehiclesArgs = new HashMap<>();
        vehiclesArgs.put("vehicle_type", vehicle.getVehicleType().name());
        vehiclesArgs.put("brand", vehicle.getBrand());
        vehiclesArgs.put("model", vehicle.getModel());
        vehiclesArgs.put("is_available", vehicle.isAvailableForRent());
        vehiclesArgs.put("year", vehicle.getYearOfProduction());
        vehiclesArgs.put("colour", vehicle.getColour().name());
        vehiclesArgs.put("price", vehicle.getRentPricePerDay());
        vehiclesArgs.put("engine_type", vehicle.getEngineType().name());
        vehiclesArgs.put("plate_number", vehicle.getPlateNumber());
        vehiclesArgs.put("transmission_type", vehicle.getTransmissionType().name());
        return insertIntoVehicles.executeAndReturnKey(vehiclesArgs).longValue();

//        jdbcTemplate.update("INSERT INTO vehicles (vehicle_type, brand, model, is_available, year, colour, price, " +
//                        "engine_type, plate_number, transmission_type) " +
//                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
//                vehicle.getVehicleType().name(), vehicle.getBrand(), vehicle.getModel(), vehicle.isAvailableForRent(),
//                vehicle.getYearOfProduction(), vehicle.getColour().name(), vehicle.getRentPricePerDay(),
//                vehicle.getEngineType().name(), vehicle.getPlateNumber(), vehicle.getTransmissionType().name());
    }
}
