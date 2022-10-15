package lv.javaguru.java2.rentapp.core.services.search_criterias;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lv.javaguru.java2.rentapp.domain.MiniBus;
import lv.javaguru.java2.rentapp.domain.PassengerCar;
import lv.javaguru.java2.rentapp.domain.Vehicle;

@Getter
@EqualsAndHashCode
public class ConditionerCriteria implements SearchCriteria {

    private String hasConditioner;

    public ConditionerCriteria(String hasConditioner) {
        this.hasConditioner = hasConditioner;
    }

    @Override
    public boolean test(Vehicle vehicle) {
        if (hasConditioner.equals("true")) {
            if (vehicle instanceof PassengerCar) {
                return ((PassengerCar) vehicle).isAirConditioningAvailable();
            } else if (vehicle instanceof MiniBus) {
                return ((MiniBus) vehicle).isAirConditioningAvailable();
            }
        } else if (hasConditioner.equals("false")) {
            if (vehicle instanceof PassengerCar) {
                return !((PassengerCar) vehicle).isAirConditioningAvailable();
            } else if (vehicle instanceof MiniBus) {
                return !((MiniBus) vehicle).isAirConditioningAvailable();
            }
        }
        return false;
    }
}

