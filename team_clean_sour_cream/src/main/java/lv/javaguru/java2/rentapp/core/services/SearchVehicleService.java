package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.SearchVehicleResponse;
import lv.javaguru.java2.rentapp.core.services.search_criterias.*;
import lv.javaguru.java2.rentapp.core.services.search_criterias.car_trailer_criteria.*;
import lv.javaguru.java2.rentapp.domain.Vehicle;

import java.util.List;

public class SearchVehicleService {

    private Database database;

    public SearchVehicleService(Database database) {
        this.database = database;
    }

    public SearchVehicleResponse execute(SearchVehicleRequest request) {
        SearchCriteria andCriteria = getSearchCriteria(request);
        List<Vehicle> response = database.search(andCriteria);
        return new SearchVehicleResponse(response, null);

    }

    private SearchCriteria getSearchCriteria(SearchVehicleRequest request) {
        SearchCriteria andCriteria = new AndSearchCriteria(new VehicleTypeCriteria(request.getVehicleType()), null);

        if (request.getBaggageAmount() != null)  {
             andCriteria = new AndSearchCriteria(andCriteria, new BaggageAmountCriteria(request.getBaggageAmount()));
        }
        if (request.getDoorsAmount() != null)  {
            andCriteria = new AndSearchCriteria(andCriteria, new DoorsAmountCriteria(request.getDoorsAmount()));
        }
        if (request.getPassengerAmount() != null)  {
            andCriteria = new AndSearchCriteria(andCriteria, new PassengerAmountCriteria(request.getPassengerAmount()));
        }
        if (request.getTransmissionType() != null)  {
            andCriteria = new AndSearchCriteria(andCriteria, new TransmissionTypeCriteria(request.getTransmissionType()));
        }
        if (request.getHasConditioner() != null)  {
            andCriteria = new AndSearchCriteria(andCriteria, new ConditionerCriteria(request.getHasConditioner()));
        }
        if (request.getDeckHeightInCm() != null) {
            andCriteria = new AndSearchCriteria(andCriteria, new DeckHeightCriteria(request.getDeckHeightInCm()));
        }
        if (request.getDeckLengthInCm() != null) {
            andCriteria = new AndSearchCriteria(andCriteria, new DeckLengthCriteria(request.getDeckLengthInCm()));
        }
        if (request.getDeckWidthInCm() != null) {
            andCriteria = new AndSearchCriteria(andCriteria, new DeckWidthCriteria(request.getDeckWidthInCm()));
        }
        if (request.getEmptyWeightInKg() != null) {
            andCriteria = new AndSearchCriteria(andCriteria, new EmptyWeightCriteria(request.getEmptyWeightInKg()));
        }
        if (request.getMaxLoadWeightInKg() != null) {
            andCriteria = new AndSearchCriteria(andCriteria, new MaxLoadWeightCriteria(request.getMaxLoadWeightInKg()));
        }
        return andCriteria;
    }
}
