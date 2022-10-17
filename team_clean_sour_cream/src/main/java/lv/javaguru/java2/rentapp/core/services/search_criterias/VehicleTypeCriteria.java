package lv.javaguru.java2.rentapp.core.services.search_criterias;

import lombok.EqualsAndHashCode;
import lv.javaguru.java2.rentapp.domain.*;
import lv.javaguru.java2.rentapp.enums.VehicleType;

@EqualsAndHashCode
public class VehicleTypeCriteria implements SearchCriteria {

    private String vehicleType;

    public VehicleTypeCriteria(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public boolean test(Vehicle vehicle) {
        if (vehicleType.equalsIgnoreCase(VehicleType.PASSENGER_CAR.getNameVehicleType())) {
            return vehicle instanceof PassengerCar;
        }
        if (vehicleType.equalsIgnoreCase(VehicleType.MINIBUS.getNameVehicleType())) {
            return vehicle instanceof MiniBus;
        }
        if (vehicleType.equalsIgnoreCase(VehicleType.MOTORCYCLE.getNameVehicleType())) {
            return vehicle instanceof Motorcycle;
        }
        if (vehicleType.equalsIgnoreCase(VehicleType.CAR_TRAILER.getNameVehicleType())) {
            return vehicle instanceof CarTrailer;
        }
        return false;
    }
}
