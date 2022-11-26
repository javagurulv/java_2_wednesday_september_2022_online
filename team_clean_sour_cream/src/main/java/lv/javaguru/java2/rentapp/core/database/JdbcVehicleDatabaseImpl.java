package lv.javaguru.java2.rentapp.core.database;

import lv.javaguru.java2.rentapp.core.services.search_criterias.SearchCriteria;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JdbcVehicleDatabaseImpl implements VehicleDatabase{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addNewVehicle(Vehicle vehicle) {
        vehicle.getVehicleType();
        jdbcTemplate.update(
                "INSERT INTO vehicles (title, author)"
                        + "VALUES (?, ?)",
                book.getTitle(), book.getAuthor()
        );
    }

    @Override
    public void deleteVehicleByPlateNumber(String plateNumber) {

    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return null;
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
