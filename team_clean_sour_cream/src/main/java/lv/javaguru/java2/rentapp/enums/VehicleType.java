package lv.javaguru.java2.rentapp.enums;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public enum VehicleType {

    PASSENGER_CAR("Passenger Car"),
    MINIBUS("Mini Bus"),
    MOTORCYCLE("Motorcycle"),
    CAR_TRAILER("Car Trailer");

    private String nameVehicleType;

    VehicleType(String nameVehicleTypeString) {
        this.nameVehicleType = nameVehicleTypeString;
    }

    public String getNameVehicleType() {
        return nameVehicleType;
    }

    public static List<String> getAllEnumValues() {
        return EnumSet.allOf(VehicleType.class).stream()
                .map(VehicleType::getNameVehicleType)
                .collect(Collectors.toList());
    }
}