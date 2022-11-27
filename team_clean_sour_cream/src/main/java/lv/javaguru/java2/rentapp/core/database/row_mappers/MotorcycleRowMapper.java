package lv.javaguru.java2.rentapp.core.database.row_mappers;

import lv.javaguru.java2.rentapp.domain.Motorcycle;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MotorcycleRowMapper extends VehicleRowMapper {

    @Override
    public Motorcycle mapRow(ResultSet rs, int rowNum) throws SQLException {
        Motorcycle motorcycle = new Motorcycle();
        getVehicle(rs, motorcycle);
        motorcycle.setPassengerAmount(rs.getInt("passenger_amount"));
        return motorcycle;
    }
}