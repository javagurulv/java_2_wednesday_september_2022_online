package lv.javaguru.java2.rentapp.core.services.search_criterias;

import lv.javaguru.java2.rentapp.domain.MiniBus;
import lv.javaguru.java2.rentapp.domain.Motorcycle;
import lv.javaguru.java2.rentapp.domain.PassengerCar;
import lv.javaguru.java2.rentapp.domain.Vehicle;

public class PassengerAmountCriteria implements SearchCriteria {

    private Integer passengerAmount;

    public PassengerAmountCriteria(Integer passengerAmount) {
        this.passengerAmount = passengerAmount;
    }

    @Override
    public boolean test(Vehicle vehicle) {
        if (vehicle instanceof PassengerCar) {
            return ((PassengerCar) vehicle).getPassengerAmount().equals(passengerAmount);
        } else if (vehicle instanceof MiniBus) {
            return ((MiniBus) vehicle).getPassengerAmount().equals(passengerAmount);
        } else if (vehicle instanceof Motorcycle) {
            return ((Motorcycle) vehicle).getPassengerAmount().equals(passengerAmount);
        } else {
            return false;
        }
    }
}
