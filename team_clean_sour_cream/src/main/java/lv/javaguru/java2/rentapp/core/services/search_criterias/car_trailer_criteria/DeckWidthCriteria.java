package lv.javaguru.java2.rentapp.core.services.search_criterias.car_trailer_criteria;

import lv.javaguru.java2.rentapp.core.services.search_criterias.SearchCriteria;
import lv.javaguru.java2.rentapp.domain.CarTrailer;
import lv.javaguru.java2.rentapp.domain.Vehicle;

public class DeckWidthCriteria implements SearchCriteria {

    private Integer deckWidthInCm;

    public DeckWidthCriteria(Integer deckLengthInCm) {
        this.deckWidthInCm = deckLengthInCm;
    }

    @Override
    public boolean test(Vehicle vehicle) {
        if (vehicle instanceof CarTrailer) {
            return ((CarTrailer) vehicle).getDeckWidthInCm().equals(deckWidthInCm);
        } else
            return false;
    }
}
