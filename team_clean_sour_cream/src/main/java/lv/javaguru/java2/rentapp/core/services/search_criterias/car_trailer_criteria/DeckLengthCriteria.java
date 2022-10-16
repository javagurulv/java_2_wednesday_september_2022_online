package lv.javaguru.java2.rentapp.core.services.search_criterias.car_trailer_criteria;

import lv.javaguru.java2.rentapp.core.services.search_criterias.SearchCriteria;
import lv.javaguru.java2.rentapp.domain.CarTrailer;

import lv.javaguru.java2.rentapp.domain.Vehicle;

public class DeckLengthCriteria implements SearchCriteria {

    private Integer deckLengthInCm;

    public DeckLengthCriteria(Integer deckLengthInCm) {
        this.deckLengthInCm = deckLengthInCm;
    }

    @Override
    public boolean test(Vehicle vehicle) {
        if (vehicle instanceof CarTrailer) {
            return ((CarTrailer) vehicle).getDeckLengthInCm().equals(deckLengthInCm);
        } else
            return false;
    }
}
