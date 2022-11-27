package lv.javaguru.java2.rentapp.core.database.row_mappers;

import lv.javaguru.java2.rentapp.domain.CarTrailer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarTrailerRowMapper extends VehicleRowMapper {

    @Override
    public CarTrailer mapRow(ResultSet rs, int rowNum) throws SQLException {
        CarTrailer carTrailer = new CarTrailer();
        getVehicle(rs, carTrailer);
        carTrailer.setDeckWidthInCm(rs.getInt("deck_width_cm"));
        carTrailer.setDeckLengthInCm(rs.getInt("deck_length_cm"));
        carTrailer.setDeckHeightInCm(rs.getInt("deck_height_cm"));
        carTrailer.setEmptyWeightInKg(rs.getInt("empty_weight_kg"));
        carTrailer.setMaxLoadWeightInKg(rs.getInt("max_weight_kg"));
        return carTrailer;
    }
}
