package lv.javaguru.java2.rentapp.core.database.row_mappers;

import lv.javaguru.java2.rentapp.domain.PassengerCar;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PassengerCarRowMapper extends VehicleRowMapper {

    @Override
    public PassengerCar mapRow(ResultSet rs, int rowNum) throws SQLException {
        PassengerCar passengerCar = new PassengerCar();
        getVehicle(rs, passengerCar);
        passengerCar.setPassengerAmount(rs.getInt("passenger_amount"));
        passengerCar.setBaggageAmount(rs.getInt("baggageAmount"));
        passengerCar.setDoorsAmount(rs.getInt("doorsAmount"));
        passengerCar.setAirConditioningAvailable(rs.getBoolean("air_conditioning"));
        return passengerCar;
    }
}
