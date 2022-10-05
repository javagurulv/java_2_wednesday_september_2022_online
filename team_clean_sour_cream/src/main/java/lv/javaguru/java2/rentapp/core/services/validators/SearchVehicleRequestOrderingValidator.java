package lv.javaguru.java2.rentapp.core.services.validators;

import lv.javaguru.java2.rentapp.core.requests.Ordering;
import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchVehicleRequestOrderingValidator {

    public List<CoreError> validate(SearchVehicleRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateMandatoryOrderBy(request.getOrdering()).ifPresent(errors::add);
        validateMandatoryOrderDirection(request.getOrdering()).ifPresent(errors::add);
        validateOrderBy(request.getOrdering()).ifPresent(errors::add);
        validateOrderDirection(request.getOrdering()).ifPresent(errors::add);
        return errors;
    }

    protected Optional<CoreError> validateMandatoryOrderBy(Ordering ordering) {
        return ((ordering.getOrderDirection() != null && !ordering.getOrderDirection().isBlank())
                && (ordering.getOrderBy() == null || ordering.getOrderBy().isBlank()))
                ? Optional.of(new CoreError("orderBy", "Must not be empty!"))
                : Optional.empty();
    }

    protected Optional<CoreError> validateMandatoryOrderDirection(Ordering ordering) {
        return ((ordering.getOrderBy() != null && !ordering.getOrderBy().isBlank())
                && (ordering.getOrderDirection() == null || ordering.getOrderDirection().isBlank()))
                ? Optional.of(new CoreError("orderDirection", "Must not be empty!"))
                : Optional.empty();
    }

    protected Optional<CoreError> validateOrderBy(Ordering ordering) {
        return (ordering.getOrderBy() != null
                && !(ordering.getOrderBy().equalsIgnoreCase("price") || ordering.getOrderBy().equalsIgnoreCase("year")))
                ? Optional.of(new CoreError("orderBy", "Must contain 'price' or 'year' only!"))
                : Optional.empty();
    }

    protected Optional<CoreError> validateOrderDirection(Ordering ordering) {
        return (ordering.getOrderDirection() != null
                && !(ordering.getOrderDirection().equalsIgnoreCase("ASCENDING") || ordering.getOrderDirection().equalsIgnoreCase("DESCENDING")))
                ? Optional.of(new CoreError("orderDirection", "Must contain 'ASCENDING' or 'DESCENDING' only!"))
                : Optional.empty();
    }

}
