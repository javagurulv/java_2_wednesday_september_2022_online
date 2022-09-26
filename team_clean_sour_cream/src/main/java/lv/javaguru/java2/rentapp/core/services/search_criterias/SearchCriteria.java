package lv.javaguru.java2.rentapp.core.services.search_criterias;

import lv.javaguru.java2.rentapp.domain.Vehicle;

import java.util.function.Predicate;

public interface SearchCriteria extends Predicate<Vehicle> {

    boolean test(Vehicle vehicle);

}
