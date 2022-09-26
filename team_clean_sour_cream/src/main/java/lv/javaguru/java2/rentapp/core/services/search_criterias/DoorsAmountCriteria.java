package lv.javaguru.java2.rentapp.core.services.search_criterias;

import lv.javaguru.java2.rentapp.domain.MiniBus;
import lv.javaguru.java2.rentapp.domain.PassengerCar;
import lv.javaguru.java2.rentapp.domain.Vehicle;

public class DoorsAmountCriteria implements SearchCriteria{

    private Integer doorsAmount;

    public DoorsAmountCriteria(Integer doorsAmount) {
        this.doorsAmount = doorsAmount;
    }


    @Override
    public boolean test(Vehicle vehicle) {
        if (vehicle instanceof PassengerCar){
                return ((PassengerCar) vehicle).getDoorsAmount().equals(doorsAmount);
        } else if(vehicle instanceof MiniBus) {
            return ((MiniBus) vehicle).getDoorsAmount().equals(doorsAmount);
        } else {
            return false;
        }
    }
}
