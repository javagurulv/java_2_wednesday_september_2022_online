package lv.javaguru.java2.rentapp.enums;

public enum VehicleType {

    PASSENGER_CAR("Passenger car"),
    MINIBUS("Mini bus"),
    MOTORCYCLE("Motorcycle"),
    CAR_TRAILER("Car trailer");

    private String nameVehicleType;

    VehicleType(String nameVehicleType) {
        this.nameVehicleType = nameVehicleType;
    }

    public String getNameVehicleType() {
        return nameVehicleType;
    }
}
