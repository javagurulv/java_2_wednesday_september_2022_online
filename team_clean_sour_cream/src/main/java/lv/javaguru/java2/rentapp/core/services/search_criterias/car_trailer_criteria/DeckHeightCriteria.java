package lv.javaguru.java2.rentapp.core.services.search_criterias.car_trailer_criteria;

import lv.javaguru.java2.rentapp.core.services.search_criterias.SearchCriteria;
import lv.javaguru.java2.rentapp.domain.CarTrailer;
import lv.javaguru.java2.rentapp.domain.Vehicle;

public class DeckHeightCriteria implements SearchCriteria {

    private Integer deckHeightInCm;

    public DeckHeightCriteria(Integer deckHeightInCm) {
        this.deckHeightInCm = deckHeightInCm;
    }

    @Override
    public boolean test(Vehicle vehicle) {
        if (vehicle instanceof CarTrailer) {
            return ((CarTrailer) vehicle).getDeckHeightInCm().equals(deckHeightInCm);
        } else
            return false;
    }
}
