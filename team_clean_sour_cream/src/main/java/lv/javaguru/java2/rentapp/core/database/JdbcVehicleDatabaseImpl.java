package lv.javaguru.java2.rentapp.core.database;

import lv.javaguru.java2.rentapp.core.database.row_mappers.MiniBusRowMapper;
import lv.javaguru.java2.rentapp.core.database.row_mappers.PassengerCarRowMapper;
import lv.javaguru.java2.rentapp.core.services.search_criterias.SearchCriteria;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import lv.javaguru.java2.rentapp.enums.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Component
public class JdbcVehicleDatabaseImpl implements VehicleDatabase {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addNewVehicle(Vehicle vehicle) {
        Integer isAvailable = (vehicle.isAvailableForRent()) ? 1 : 0;
        VehicleType vehicleType = vehicle.getVehicleType();

        jdbcTemplate.update("INSERT INTO vehicles (vehicle_type, brand, model, is_available, year, colour, price, " +
                        "engine_type, plate_number, transmission_type)"
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", vehicleType.getNameVehicleType(), vehicle.getBrand(),
                vehicle.getModel(), isAvailable, vehicle.getYearOfProduction(), vehicle.getColour().getNameColour(),
                vehicle.getRentPricePerDay(), vehicle.getEngineType().getNameEngineType(), vehicle.getPlateNumber(),
                vehicle.getTransmissionType().getNameTransmissionType()
        );
    }

    @Override
    public void deleteVehicleByPlateNumber(String plateNumber) {

    }

    @Override
    public List<Vehicle> getAllVehicles() {
        String sqlPassengerCars = "SELECT * FROM vehicles WHERE vehicle_type = 'PASSENGER_CAR'";
        List<Vehicle> passengerCars = jdbcTemplate.query(sqlPassengerCars, new PassengerCarRowMapper());
        String sqlMiniBuses = "SELECT * FROM vehicles WHERE vehicle_type = 'MINIBUS'";
        List<Vehicle> miniBuses = jdbcTemplate.query(sqlMiniBuses, new MiniBusRowMapper());

        return Stream.concat(passengerCars.stream(), miniBuses.stream()).toList();
    }

    @Override
    public List<Vehicle> search(SearchCriteria searchCriteria) {
        return null;
    }

    @Override
    public Optional<Vehicle> getById(Long id) {
        return Optional.empty();
    }
}
