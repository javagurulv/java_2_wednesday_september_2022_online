package lv.javaguru.java2.rentapp.core.services.search_criterias;

import lv.javaguru.java2.rentapp.domain.*;


public class VehicleTypeCriteria implements SearchCriteria {

    private String vehicleType;

    public VehicleTypeCriteria(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public boolean test(Vehicle vehicle) {
        if (vehicleType.equalsIgnoreCase("PassengerCar")) {
            return vehicle instanceof PassengerCar;
        }
        if (vehicleType.equalsIgnoreCase("MiniBus")) {
            return vehicle instanceof MiniBus;
        }
        if (vehicleType.equalsIgnoreCase("Motorcycle")) {
            return vehicle instanceof Motorcycle;
        }
        if (vehicleType.equalsIgnoreCase("CarTrailer")) {
            return vehicle instanceof CarTrailer;
        }
        return false;
    }
}
