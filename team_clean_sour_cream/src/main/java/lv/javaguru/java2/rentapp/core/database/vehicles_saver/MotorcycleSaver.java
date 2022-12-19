package lv.javaguru.java2.rentapp.core.database.vehicles_saver;

import lv.javaguru.java2.rentapp.domain.Motorcycle;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Component
public class MotorcycleSaver extends VehicleSaver {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public Long save(Vehicle vehicle) {
        Long vehicleId = saveCommonFields(vehicle);
        Motorcycle motorcycle = (Motorcycle) vehicle;
        SimpleJdbcInsert insertIntoMotorcycles = new SimpleJdbcInsert(jdbcTemplate).withTableName("motorcycles").usingGeneratedKeyColumns("id");
        Map<String, Object> motorcyclesArgs = new HashMap<>();
        motorcyclesArgs.put("vehicle_id", vehicleId);
        motorcyclesArgs.put("vehicle_type", motorcycle.getVehicleType().name());
        motorcyclesArgs.put("passenger_amount", motorcycle.getPassengerAmount());
        insertIntoMotorcycles.execute(motorcyclesArgs);
        return vehicleId;
    }
}
