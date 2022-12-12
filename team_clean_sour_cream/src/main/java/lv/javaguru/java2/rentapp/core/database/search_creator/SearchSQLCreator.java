package lv.javaguru.java2.rentapp.core.database.search_creator;

import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import org.springframework.stereotype.Component;

@Component
public class SearchSQLCreator {

    public String getSQLSearchCriteria(SearchVehicleRequest request) {

        String secondTableName = getSecondTableName(request);
        String vehicleType = request.getVehicleType().name();
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM vehicles JOIN " + secondTableName + " on vehicles.id" +
                " = " + secondTableName + ".vehicle_id WHERE vehicles.vehicle_type = '" + vehicleType + "'");

        if (request.getBaggageAmount() != null) {
            sqlBuilder.append(" AND ").append(secondTableName).append(".baggageAmount = ").append(request.getBaggageAmount().toString());
        }
        if (request.getDoorsAmount() != null) {
            sqlBuilder.append(" AND ").append(secondTableName).append(".doorsAmount = ").append(request.getDoorsAmount().toString());
        }
        if (request.getPassengerAmount() != null) {
            sqlBuilder.append(" AND ").append(secondTableName).append(".passenger_amount = ").append(request.getPassengerAmount().toString());
        }
        if (request.getTransmissionType() != null) {
            sqlBuilder.append(" AND ").append("vehicles").append(".transmission_type = '").append(request.getTransmissionType()).append("'");
        }
        if (request.getHasConditioner() != null) {
            sqlBuilder.append(" AND ").append(secondTableName).append(".air_conditioning = ").append(request.getHasConditioner());
        }
        if (request.getDeckHeightInCm() != null) {
            sqlBuilder.append(" AND ").append(secondTableName).append(".deck_height_cm = ").append(request.getDeckHeightInCm().toString());
        }
        if (request.getDeckLengthInCm() != null) {
            sqlBuilder.append(" AND ").append(secondTableName).append(".deck_length_cm = ").append(request.getDeckLengthInCm().toString());
        }
        if (request.getDeckWidthInCm() != null) {
            sqlBuilder.append(" AND ").append(secondTableName).append(".deck_width_cm = ").append(request.getDeckWidthInCm().toString());
        }
        if (request.getEmptyWeightInKg() != null) {
            sqlBuilder.append(" AND ").append(secondTableName).append(".empty_weight_kg = ").append(request.getEmptyWeightInKg().toString());
        }
        if (request.getMaxLoadWeightInKg() != null) {
            sqlBuilder.append(" AND ").append(secondTableName).append(".max_weight_kg = ").append(request.getMaxLoadWeightInKg().toString());
        }
        return sqlBuilder.toString();
    }

    String getSecondTableName(SearchVehicleRequest request) {
        return switch (request.getVehicleType()) {
            case PASSENGER_CAR -> "passenger_cars";
            case MINIBUS -> "mini_buses";
            case MOTORCYCLE -> "motorcycles";
            case CAR_TRAILER -> "car_trailers";
        };
    }
}
