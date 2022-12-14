package lv.javaguru.java2.rentapp.core.database.row_mappers;

import lv.javaguru.java2.rentapp.core.database.ClientDatabase;
import lv.javaguru.java2.rentapp.core.database.VehicleDatabase;
import lv.javaguru.java2.rentapp.domain.Client;
import lv.javaguru.java2.rentapp.domain.RentDeal;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class RentDealRowMapper implements RowMapper<RentDeal> {

    @Autowired private VehicleDatabase vehicleDatabase;
    @Autowired private ClientDatabase clientDatabase;

    @Override
    public RentDeal mapRow(ResultSet rs, int rowNum) throws SQLException {
        Optional<Vehicle> vehicle = vehicleDatabase.getById(rs.getLong("vehicle_id"));
        Optional<Client> client = clientDatabase.getById(rs.getLong("client_id"));

        RentDeal rentDeal = new RentDeal();
        rentDeal.setId(rs.getLong("id"));
        rentDeal.setVehicle(vehicle.get());
        rentDeal.setClient(client.get());
        rentDeal.setStartDate(rs.getDate("start_date").toLocalDate());
        rentDeal.setRentDuration(rs.getLong("duration"));
        rentDeal.setEndDate(rs.getDate("end_date").toLocalDate());
        rentDeal.setRentCost(rs.getDouble("cost"));
        return rentDeal;
    }
}
