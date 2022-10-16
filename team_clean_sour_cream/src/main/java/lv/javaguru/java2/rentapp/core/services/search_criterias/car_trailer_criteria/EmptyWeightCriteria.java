package lv.javaguru.java2.rentapp.core.services.search_criterias.car_trailer_criteria;

import lv.javaguru.java2.rentapp.core.services.search_criterias.SearchCriteria;
import lv.javaguru.java2.rentapp.domain.CarTrailer;
import lv.javaguru.java2.rentapp.domain.Vehicle;

public class EmptyWeightCriteria implements SearchCriteria {

    private Integer emptyWeightInKg;

    public EmptyWeightCriteria(Integer emptyWeightInKg) {
        this.emptyWeightInKg = emptyWeightInKg;
    }

    @Override
    public boolean test(Vehicle vehicle) {
        if (vehicle instanceof CarTrailer) {
            return ((CarTrailer) vehicle).getEmptyWeightInKg().equals(emptyWeightInKg);
        } else
            return false;
    }
}
