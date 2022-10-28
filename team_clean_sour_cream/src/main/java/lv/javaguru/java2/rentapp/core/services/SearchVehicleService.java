package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.VehicleDatabase;
import lv.javaguru.java2.rentapp.core.requests.Ordering;
import lv.javaguru.java2.rentapp.core.requests.Paging;
import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.responses.SearchVehicleResponse;
import lv.javaguru.java2.rentapp.core.services.search_criterias.*;
import lv.javaguru.java2.rentapp.core.services.search_criterias.car_trailer_criteria.*;
import lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators.SearchVehicleValidator;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchVehicleService {

    @Autowired
    private VehicleDatabase vehicleDatabase;
    @Autowired
    private SearchVehicleValidator validator;

    public SearchVehicleResponse execute(SearchVehicleRequest request) {

        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new SearchVehicleResponse(null, errors);
        }

        SearchCriteria andCriteria = getSearchCriteria(request);
        List<Vehicle> vehicles = vehicleDatabase.search(andCriteria);

        vehicles = order(vehicles, request.getOrdering());
        vehicles = paging(vehicles, request.getPaging());
        return new SearchVehicleResponse(vehicles, null);
    }

    private List<Vehicle> paging(List<Vehicle> vehicles, Paging paging) {
        if (paging != null) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return vehicles.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return vehicles;
        }
    }

    private List<Vehicle> order(List<Vehicle> vehicles, Ordering ordering) {
        if (ordering != null) {
            Comparator<Vehicle> comparator = ordering.getOrderBy().equalsIgnoreCase("price")
                    ? Comparator.comparing(Vehicle::getRentPricePerDay)
                    : Comparator.comparing(Vehicle::getYearOfProduction);
            if (ordering.getOrderDirection().equalsIgnoreCase("DESC")) {
                comparator = comparator.reversed();
            }
            return vehicles.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return vehicles;
        }
    }

    private SearchCriteria getSearchCriteria(SearchVehicleRequest request) {

        SearchCriteria andCriteria = new AndSearchCriteria(new VehicleTypeCriteria(request.getVehicleType().getNameVehicleType()), null);

        if (request.getBaggageAmount() != null) {
            andCriteria = new AndSearchCriteria(andCriteria, new BaggageAmountCriteria(request.getBaggageAmount()));
        }
        if (request.getDoorsAmount() != null) {
            andCriteria = new AndSearchCriteria(andCriteria, new DoorsAmountCriteria(request.getDoorsAmount()));
        }
        if (request.getPassengerAmount() != null) {
            andCriteria = new AndSearchCriteria(andCriteria, new PassengerAmountCriteria(request.getPassengerAmount()));
        }
        if (request.getTransmissionType() != null) {
            andCriteria = new AndSearchCriteria(andCriteria, new TransmissionTypeCriteria(request.getTransmissionType()));
        }
        if (request.getHasConditioner() != null) {
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
