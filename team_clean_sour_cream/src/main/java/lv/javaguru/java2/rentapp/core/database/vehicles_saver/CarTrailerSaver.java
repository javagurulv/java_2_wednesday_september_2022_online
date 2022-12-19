package lv.javaguru.java2.rentapp.core.database.vehicles_saver;

import lv.javaguru.java2.rentapp.domain.CarTrailer;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Component
public class CarTrailerSaver extends VehicleSaver {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public Long save(Vehicle vehicle) {
        Long vehicleId = saveCommonFields(vehicle);
        CarTrailer carTrailer = (CarTrailer) vehicle;
        SimpleJdbcInsert insertIntoCarTrailer = new SimpleJdbcInsert(jdbcTemplate).withTableName("car_trailers").usingGeneratedKeyColumns("id");
        Map<String, Object> carTrailerArgs = new HashMap<>();
        carTrailerArgs.put("vehicle_id", vehicleId);
        carTrailerArgs.put("vehicle_type", carTrailer.getVehicleType().name());
        carTrailerArgs.put("deck_width_cm", carTrailer.getDeckWidthInCm());
        carTrailerArgs.put("deck_length_cm", carTrailer.getDeckLengthInCm());
        carTrailerArgs.put("deck_height_cm", carTrailer.getDeckHeightInCm());
        carTrailerArgs.put("empty_weight_kg", carTrailer.getEmptyWeightInKg());
        carTrailerArgs.put("max_weight_kg", carTrailer.getMaxLoadWeightInKg());
        insertIntoCarTrailer.execute(carTrailerArgs);
        return vehicleId;
    }
}