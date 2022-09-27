package lv.javaguru.java2.rentapp.core.services.search_criterias;

import lv.javaguru.java2.rentapp.domain.MiniBus;
import lv.javaguru.java2.rentapp.domain.PassengerCar;
import lv.javaguru.java2.rentapp.domain.Vehicle;

public class ConditionerCriteria implements SearchCriteria {

    @Override
    public boolean test(Vehicle vehicle) {
        if (vehicle instanceof PassengerCar) {
            return ((PassengerCar) vehicle).isAirConditioningAvailable();
        } else if (vehicle instanceof MiniBus) {
            return ((MiniBus) vehicle).isAirConditioningAvailable();
        } else return false;
    }
}
