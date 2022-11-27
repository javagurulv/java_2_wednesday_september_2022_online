package lv.javaguru.java2.rentapp.core.database.row_mappers;

import lv.javaguru.java2.rentapp.domain.MiniBus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MiniBusRowMapper extends VehicleRowMapper {

    @Override
    public MiniBus mapRow(ResultSet rs, int rowNum) throws SQLException {
        MiniBus miniBus = new MiniBus();
        getVehicle(rs, miniBus);
        miniBus.setPassengerAmount(rs.getInt("passenger_amount"));
        miniBus.setBaggageAmount(rs.getInt("baggageAmount"));
        miniBus.setDoorsAmount(rs.getInt("doorsAmount"));
        miniBus.setAirConditioningAvailable(rs.getBoolean("air_conditioning"));
        return miniBus;
    }
}
