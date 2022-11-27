package lv.javaguru.java2.rentapp.core.database.row_mappers;

import lv.javaguru.java2.rentapp.domain.CarTrailer;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import lv.javaguru.java2.rentapp.enums.Colour;
import lv.javaguru.java2.rentapp.enums.EngineType;
import lv.javaguru.java2.rentapp.enums.TransmissionType;
import lv.javaguru.java2.rentapp.enums.VehicleType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarTrailerRowMapper extends VehicleRowMapper {

    @Override
    public CarTrailer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Vehicle carTrailer = new CarTrailer();
        getVehicle(rs, carTrailer);
        carTrailer.get
        return carTrailer;
    }

}
