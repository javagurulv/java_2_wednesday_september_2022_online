package lv.javaguru.java2.rentapp.core.database;

import lv.javaguru.java2.rentapp.core.database.row_mappers.CarTrailerRowMapper;
import lv.javaguru.java2.rentapp.core.database.row_mappers.MiniBusRowMapper;
import lv.javaguru.java2.rentapp.core.database.row_mappers.MotorcycleRowMapper;
import lv.javaguru.java2.rentapp.core.database.row_mappers.PassengerCarRowMapper;
import lv.javaguru.java2.rentapp.core.services.search_criterias.SearchCriteria;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JdbcVehicleDatabaseImpl implements VehicleDatabase {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Long addNewVehicle(Vehicle vehicle) {

        int update = jdbcTemplate.update("INSERT INTO vehicles (vehicle_type, brand, model, is_available, year, colour, price, engine_type, plate_number, transmission_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
                        + "INSERT INTO passenger_cars (`vehicle_id`, `passenger_amount`, `baggageAmount`, `doorsAmount`, `air_conditioning`) VALUES (?, ?, ?, ?, ?)", vehicle.getVehicleType().name(), vehicle.getBrand(),
                vehicle.getModel(), vehicle.isAvailableForRent(), vehicle.getYearOfProduction(), vehicle.getColour().getNameColour(),
                vehicle.getRentPricePerDay(), vehicle.getEngineType().getNameEngineType(), vehicle.getPlateNumber(),
                vehicle.getTransmissionType().getNameTransmissionType() + "INSERT INTO passenger_cars (`vehicle_id`, `passenger_amount`, `baggageAmount`, `doorsAmount`, `air_conditioning`)"
                        + "VALUES (?, ?, ?, ?, ?);"
        );
        return (long) update;
    }

    @Override
    public void deleteVehicleByPlateNumber(String plateNumber) {

    }

    @Override
    public List<Vehicle> getAllVehicles() {
        String sqlPassengerCars = "SELECT * FROM vehicles JOIN passenger_cars pc on vehicles.id = pc.vehicle_id";
        List<Vehicle> passengerCars = jdbcTemplate.query(sqlPassengerCars, new PassengerCarRowMapper());
        String sqlMiniBuses = "SELECT * FROM vehicles JOIN mini_buses mb on vehicles.id = mb.vehicle_id";
        List<Vehicle> miniBuses = jdbcTemplate.query(sqlMiniBuses, new MiniBusRowMapper());
        String sqlMotorcycle = "SELECT * FROM vehicles JOIN motorcycles m on vehicles.id = m.vehicle_id";
        List<Vehicle> motorcycles = jdbcTemplate.query(sqlMotorcycle, new MotorcycleRowMapper());
        String sqlCarTrailer = "SELECT * FROM vehicles JOIN car_trailers ct on vehicles.id = ct.vehicle_id";
        List<Vehicle> carTrailers = jdbcTemplate.query(sqlCarTrailer, new CarTrailerRowMapper());

        return Stream.of(passengerCars, miniBuses, motorcycles, carTrailers)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
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