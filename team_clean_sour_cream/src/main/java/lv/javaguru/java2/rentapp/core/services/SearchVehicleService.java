package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.Ordering;
import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.responses.SearchVehicleResponse;
import lv.javaguru.java2.rentapp.core.services.search_criterias.*;
import lv.javaguru.java2.rentapp.core.services.search_criterias.car_trailer_criteria.*;
import lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators.SearchVehicleValidator;
import lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators.SearchVehicleValidatorMap;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import lv.javaguru.java2.rentapp.enums.VehicleType;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SearchVehicleService {

    private Database database;

    private SearchVehicleValidatorMap searchVehicleValidatorMap;

    public SearchVehicleService(Database database) {
        this.database = database;
        this.searchVehicleValidatorMap = new SearchVehicleValidatorMap();
    }

    public SearchVehicleResponse execute(SearchVehicleRequest request) {
        VehicleType vehicleType = request.getVehicleType();
        SearchVehicleValidator searchVehicleValidator = searchVehicleValidatorMap.getVehicleValidatorByCarType(vehicleType);

        List<CoreError> errors = searchVehicleValidator.validate(request);

        if (!errors.isEmpty()) {
            return new SearchVehicleResponse(null, errors);
        }

        SearchCriteria andCriteria = getSearchCriteria(request);
        List<Vehicle> vehicles = database.search(andCriteria);

        vehicles = order(vehicles, request.getOrdering());
        return new SearchVehicleResponse(vehicles, null);
    }

    private List<Vehicle> order(List<Vehicle> vehicles, Ordering ordering) {
        if (ordering != null) {
            Comparator<Vehicle> comparator = ordering.getOrderBy().equalsIgnoreCase("price")
                    ? Comparator.comparing(Vehicle::getRentPricePerDay)
                    : Comparator.comparing(Vehicle::getYearOfProduction);
            if (ordering.getOrderDirection().equalsIgnoreCase("DESCENDING")) {
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
