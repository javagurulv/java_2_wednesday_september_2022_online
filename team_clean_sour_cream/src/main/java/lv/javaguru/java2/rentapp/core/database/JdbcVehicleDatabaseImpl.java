package lv.javaguru.java2.rentapp.core.database;

import lv.javaguru.java2.rentapp.core.database.row_mappers.CarTrailerRowMapper;
import lv.javaguru.java2.rentapp.core.database.row_mappers.MiniBusRowMapper;
import lv.javaguru.java2.rentapp.core.database.row_mappers.MotorcycleRowMapper;
import lv.javaguru.java2.rentapp.core.database.row_mappers.PassengerCarRowMapper;
import lv.javaguru.java2.rentapp.core.database.vehicles_saver.CarTrailerSaver;
import lv.javaguru.java2.rentapp.core.database.vehicles_saver.MiniBusSaver;
import lv.javaguru.java2.rentapp.core.database.vehicles_saver.MotorcycleSaver;
import lv.javaguru.java2.rentapp.core.database.vehicles_saver.PassengerCarSaver;
import lv.javaguru.java2.rentapp.core.services.search_criterias.SearchCriteria;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import lv.javaguru.java2.rentapp.enums.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JdbcVehicleDatabaseImpl implements VehicleDatabase {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PassengerCarSaver passengerCarSaver;
    @Autowired
    private MiniBusSaver miniBusSaver;
    @Autowired
    private MotorcycleSaver motorcycleSaver;
    @Autowired
    private CarTrailerSaver carTrailerSaver;

    @Override
    @Transactional
    public Long addNewVehicle(Vehicle vehicle) {
        return switch (vehicle.getVehicleType()) {
            case PASSENGER_CAR -> passengerCarSaver.save(vehicle);
            case MINIBUS -> miniBusSaver.save(vehicle);
            case MOTORCYCLE -> motorcycleSaver.save(vehicle);
            case CAR_TRAILER -> carTrailerSaver.save(vehicle);
        };
    }

    @Override
    public void deleteVehicleByPlateNumber(String plateNumber) {
        String sql = "DELETE FROM vehicles WHERE plate_number = ?";
        Object[] args = new Object[]{plateNumber};
        jdbcTemplate.update(sql, args);
    }

    @Override
    @Transactional
    public List<Vehicle> getAllVehicles() {
        return Stream.of(getPassengerCars(), getMiniBuses(), getMotorcycles(), getCarTrailers())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> search(SearchCriteria searchCriteria) {
        return null;
    }

    @Override
    public Optional<Vehicle> getById(Long id) {
        String sql = "SELECT vehicle_type FROM vehicles WHERE id = ?";
        String vehicleType = jdbcTemplate.queryForObject(sql, String.class, id);
        return switch (VehicleType.valueOf(vehicleType)) {
            case PASSENGER_CAR -> Optional.of(getPassengerCarById(id));
            case MINIBUS -> Optional.of(getMiniBusById(id));
            case MOTORCYCLE -> Optional.of(getMotorcycleById(id));
            case CAR_TRAILER -> Optional.of(getCarTrailerById(id));
        };
    }

    private List<Vehicle> getPassengerCars() {
        String sqlPassengerCars = "SELECT * FROM vehicles JOIN passenger_cars pc on vehicles.id = pc.vehicle_id";
        return jdbcTemplate.query(sqlPassengerCars, new PassengerCarRowMapper());
    }

    private Vehicle getPassengerCarById(Long passengerCarId) {
        String sqlPassengerCar = "SELECT * FROM vehicles JOIN passenger_cars pc on vehicles.id = pc.vehicle_id WHERE vehicles.id = ?";
        return jdbcTemplate.queryForObject(sqlPassengerCar, new Object[]{passengerCarId}, new PassengerCarRowMapper());
    }

    private List<Vehicle> getMiniBuses() {
        String sqlMiniBuses = "SELECT * FROM vehicles JOIN mini_buses mb on vehicles.id = mb.vehicle_id";
        return jdbcTemplate.query(sqlMiniBuses, new MiniBusRowMapper());
    }

    private Vehicle getMiniBusById(Long miniBusId) {
        String sqlMiniBus = "SELECT * FROM vehicles JOIN mini_buses mb on vehicles.id = mb.vehicle_id WHERE vehicles.id = ?";
        return jdbcTemplate.queryForObject(sqlMiniBus, new Object[]{miniBusId}, new MiniBusRowMapper());
    }

    private List<Vehicle> getMotorcycles() {
        String sqlMotorcycle = "SELECT * FROM vehicles JOIN motorcycles m on vehicles.id = m.vehicle_id";
        return jdbcTemplate.query(sqlMotorcycle, new MotorcycleRowMapper());
    }

    private Vehicle getMotorcycleById(Long motorcycleId) {
        String sqlMotorcycle = "SELECT * FROM vehicles JOIN motorcycles m on vehicles.id = m.vehicle_id WHERE vehicles.id = ?";
        return jdbcTemplate.queryForObject(sqlMotorcycle, new Object[]{motorcycleId}, new MotorcycleRowMapper());
    }

    private List<Vehicle> getCarTrailers() {
        String sqlCarTrailer = "SELECT * FROM vehicles JOIN car_trailers ct on vehicles.id = ct.vehicle_id";
        return jdbcTemplate.query(sqlCarTrailer, new CarTrailerRowMapper());
    }

    private Vehicle getCarTrailerById(Long carTrailerId) {
        String sqlCarTrailer = "SELECT * FROM vehicles JOIN car_trailers ct on vehicles.id = ct.vehicle_id WHERE vehicles.id = ?";
        return jdbcTemplate.queryForObject(sqlCarTrailer, new Object[]{carTrailerId}, new CarTrailerRowMapper());
    }
}