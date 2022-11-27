package lv.javaguru.java2.rentapp.core.database.row_mappers;

import lv.javaguru.java2.rentapp.domain.Vehicle;
import lv.javaguru.java2.rentapp.enums.Colour;
import lv.javaguru.java2.rentapp.enums.EngineType;
import lv.javaguru.java2.rentapp.enums.TransmissionType;
import lv.javaguru.java2.rentapp.enums.VehicleType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class VehicleRowMapper implements RowMapper<Vehicle> {

    @Override
    public abstract Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException;

    protected <T extends Vehicle> T getVehicle(ResultSet rs, T vehicle) throws SQLException {
        vehicle.setId(rs.getLong("id"));
        vehicle.setVehicleType(VehicleType.valueOf(rs.getString("vehicle_type")));
        vehicle.setBrand(rs.getString("brand"));
        vehicle.setModel(rs.getString("model"));
        vehicle.setAvailableForRent(rs.getBoolean("is_available"));
        vehicle.setYearOfProduction(rs.getInt("year"));
        vehicle.setColour(Colour.valueOf(rs.getString("colour")));
        vehicle.setRentPricePerDay(rs.getDouble("price"));
        vehicle.setEngineType(EngineType.valueOf(rs.getString("engine_type")));
        vehicle.setPlateNumber(rs.getString("plate_number"));
        vehicle.setTransmissionType(TransmissionType.valueOf(rs.getString("transmission_type")));
        return vehicle;
    }
}
