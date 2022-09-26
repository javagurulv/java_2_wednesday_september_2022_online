package lv.javaguru.java2.rentapp.core.services.search_criterias;

import lv.javaguru.java2.rentapp.domain.Vehicle;

public class DoorsAmountCriteria implements SearchCriteria{

    private Integer doorsAmount;

    public DoorsAmountCriteria(Integer doorsAmount) {
        this.doorsAmount = doorsAmount;
    }


    @Override
    public boolean test(Vehicle vehicle) {
        return vehicle.getDoorsAmount.equals(doorsAmount);
    }
}
