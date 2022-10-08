package lv.javaguru.java2.rentapp.core.services.validators;

import lv.javaguru.java2.rentapp.core.requests.Ordering;
import lv.javaguru.java2.rentapp.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchVehicleRequestOrderingValidator {

    public List<CoreError> validate(Ordering ordering) {
        List<CoreError> errors = new ArrayList<>();
        validateMandatoryOrderBy(ordering).ifPresent(errors::add);
        validateMandatoryOrderDirection(ordering).ifPresent(errors::add);
        validateOrderBy(ordering).ifPresent(errors::add);
        validateOrderDirection(ordering).ifPresent(errors::add);
        validateMandatoryOrderByAndOrderDirection(ordering).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateMandatoryOrderByAndOrderDirection(Ordering ordering) {
        return ((ordering.getOrderDirection() == null || ordering.getOrderDirection().isBlank())
                && (ordering.getOrderBy() == null || ordering.getOrderBy().isBlank()))
                ? Optional.of(new CoreError("\"orderBy\" and \"orderDirection\"", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryOrderBy(Ordering ordering) {
        return ((ordering.getOrderDirection() != null && !ordering.getOrderDirection().isBlank())
                && (ordering.getOrderBy() == null || ordering.getOrderBy().isBlank()))
                ? Optional.of(new CoreError("orderBy", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryOrderDirection(Ordering ordering) {
        return ((ordering.getOrderBy() != null && !ordering.getOrderBy().isBlank())
                && (ordering.getOrderDirection() == null || ordering.getOrderDirection().isBlank()))
                ? Optional.of(new CoreError("orderDirection", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateOrderBy(Ordering ordering) {
        return ((ordering.getOrderBy() != null && !ordering.getOrderBy().isBlank())
                && !(ordering.getOrderBy().equalsIgnoreCase("price") || ordering.getOrderBy().equalsIgnoreCase("year")))
                ? Optional.of(new CoreError("orderBy", "Must contain 'price' or 'year' only!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateOrderDirection(Ordering ordering) {
        return ((ordering.getOrderDirection() != null && !ordering.getOrderDirection().isBlank())
                && !(ordering.getOrderDirection().equalsIgnoreCase("ASC") || ordering.getOrderDirection().equalsIgnoreCase("DESC")))
                ? Optional.of(new CoreError("orderDirection", "Must contain 'ASC' (ASCENDING) or 'DESC' (DESCENDING) only!"))
                : Optional.empty();
    }
}
