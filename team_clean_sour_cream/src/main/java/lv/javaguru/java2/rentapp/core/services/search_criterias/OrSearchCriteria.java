package lv.javaguru.java2.rentapp.core.services.search_criterias;

import lv.javaguru.java2.rentapp.domain.Vehicle;

public class OrSearchCriteria implements SearchCriteria {

    private SearchCriteria leftCondition;
    private SearchCriteria rightCondition;

    public OrSearchCriteria(SearchCriteria leftCondition, SearchCriteria rightCondition) {
        this.leftCondition = leftCondition;
        this.rightCondition = rightCondition;
    }

    @Override
    public boolean test(Vehicle vehicle) {

        return leftCondition.test(vehicle) || rightCondition.test(vehicle);

    }
}
