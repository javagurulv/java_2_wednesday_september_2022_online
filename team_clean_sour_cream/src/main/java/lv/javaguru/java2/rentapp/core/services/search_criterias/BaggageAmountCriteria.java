package lv.javaguru.java2.rentapp.core.services.search_criterias;

import lv.javaguru.java2.rentapp.domain.MiniBus;
import lv.javaguru.java2.rentapp.domain.PassengerCar;
import lv.javaguru.java2.rentapp.domain.Vehicle;

public class BaggageAmountCriteria implements SearchCriteria {
    private Integer baggageAmount;

    public BaggageAmountCriteria(Integer baggageAmount) {
        this.baggageAmount = baggageAmount;
    }

    @Override
    public boolean test(Vehicle vehicle) {
        if (vehicle instanceof PassengerCar) {
            return ((PassengerCar) vehicle).getBaggageAmount().equals(baggageAmount);
        } else if (vehicle instanceof MiniBus) {
            return ((MiniBus) vehicle).getBaggageAmount().equals(baggageAmount);
        } else return false;
    }
}
