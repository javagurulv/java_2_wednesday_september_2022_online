package lv.javaguru.java2.rentapp.core.services.search_criterias.car_trailer_criteria;

import lv.javaguru.java2.rentapp.core.services.search_criterias.SearchCriteria;
import lv.javaguru.java2.rentapp.domain.CarTrailer;
import lv.javaguru.java2.rentapp.domain.Vehicle;

public class MaxLoadWeightCriteria implements SearchCriteria {

    private Integer maxLoadWeightInKg;

    public MaxLoadWeightCriteria(Integer maxLoadWeight) {
        this.maxLoadWeightInKg = maxLoadWeight;
    }

    @Override
    public boolean test(Vehicle vehicle) {
        if (vehicle instanceof CarTrailer) {
            return ((CarTrailer) vehicle).getMaxLoadWeightInKg().equals(maxLoadWeightInKg);
        } else
            return false;
    }
}
