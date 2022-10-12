package lv.javaguru.java2.rentapp.core.services.search_criterias;

import lombok.EqualsAndHashCode;
import lv.javaguru.java2.rentapp.domain.Vehicle;

@EqualsAndHashCode
public class AndSearchCriteria implements SearchCriteria {

    private SearchCriteria leftCondition;
    private SearchCriteria rightCondition;

    public AndSearchCriteria(SearchCriteria leftCondition, SearchCriteria rightCondition) {
        this.leftCondition = leftCondition;
        this.rightCondition = rightCondition;
    }

    @Override
    public boolean test(Vehicle vehicle) {

        return rightCondition == null ? leftCondition.test(vehicle) : leftCondition.test(vehicle) && rightCondition.test(vehicle);

    }
}
