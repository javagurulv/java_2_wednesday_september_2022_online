package lv.javaguru.java2.rentapp.core.services.search_criterias;

import lv.javaguru.java2.rentapp.domain.Vehicle;


public class VehicleTypeCriteria implements SearchCriteria {

    private String vehicleType;

    public VehicleTypeCriteria(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public boolean test(Vehicle vehicle) {

        return vehicle.getClass().getCanonicalName().equalsIgnoreCase(vehicleType);

    }
}
