package lv.javaguru.java2.rentapp.core.services.validators.add_new_vehicle_validators;

import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.enums.Colour;
import lv.javaguru.java2.rentapp.enums.EngineType;
import lv.javaguru.java2.rentapp.enums.TransmissionType;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public abstract class AddVehicleValidator {

    public abstract List<CoreError> validate(AddVehicleRequest request);

    protected Optional<CoreError> validateBrand(AddVehicleRequest request) {
        String brand = request.getBrand();
        return (brand == null || brand.isEmpty())
                ? Optional.of(new CoreError("Brand", "cannot be empty"))
                : Optional.empty();
    }

    protected Optional<CoreError> validateModel(AddVehicleRequest request) {
        String model = request.getModel();
        return (model == null || model.isEmpty())
                ? Optional.of(new CoreError("Model", "cannot be empty"))
                : Optional.empty();
    }

    protected Optional<CoreError> validateYearOfProduction(AddVehicleRequest request) {
        Integer yearOfProduction = request.getYearOfProduction();
        int minYear = LocalDate.now().getYear() - 100;
        int currentYear = LocalDate.now().getYear();
        if (yearOfProduction == null) {
            return Optional.of(new CoreError("YearOfProduction", "cannot be empty"));
        } else if (yearOfProduction < minYear) {
            return Optional.of(new CoreError("YearOfProduction", "cannot be lower than " + minYear));
        } else if (yearOfProduction > currentYear) {
            return Optional.of(new CoreError("YearOfProduction", "cannot be more than current year"));
        } else {
            return Optional.empty();
        }
    }

    protected Optional<CoreError> validateColour(AddVehicleRequest request) {
        String colour = request.getColour();
        if (colour == null || colour.isEmpty()) {
            return Optional.of(new CoreError("Colour", "cannot be empty"));
        } else if (colour.toUpperCase().replaceAll("[^a-zA-Z]", "").equals(Colour.RED.name())
                || colour.toUpperCase().replaceAll("[^a-zA-Z]", "").equals(Colour.BLACK.name())
                || colour.toUpperCase().replaceAll("[^a-zA-Z]", "").equals(Colour.BLUE.name())
                || colour.toUpperCase().replaceAll("[^a-zA-Z]", "").equals(Colour.GREEN.name())
                || colour.toUpperCase().replaceAll("[^a-zA-Z]", "").equals(Colour.ORANGE.name())
                || colour.toUpperCase().replaceAll("[^a-zA-Z]", "").equals(Colour.WHITE.name())
                || colour.toUpperCase().replaceAll("[^a-zA-Z]", "").equals(Colour.YELLOW.name())) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Colour", "must be one of the provided options (Red, black, blue, green, orange, white or yellow"));
        }
    }

    protected Optional<CoreError> validateRentPrice(AddVehicleRequest request) {
        Double rentPrice = request.getRentPricePerDay();
        if (rentPrice == null || rentPrice <= 0) {
            return Optional.of(new CoreError("Rent Price", "cannot be empty, negative or 0"));
        } else {
            return Optional.empty();
        }
    }

    protected Optional<CoreError> validateEngineType(AddVehicleRequest request) {
        String engineType = request.getEngineType();
        if (engineType == null || engineType.isEmpty()) {
            return Optional.of(new CoreError("Engine Type", "cannot be empty"));
        } else if (engineType.toUpperCase().replaceAll("[^a-zA-Z]", "").equals(EngineType.DIESEL.name())
                || engineType.toUpperCase().replaceAll("[^a-zA-Z]", "").equals(EngineType.ELECTRIC.name())
                || engineType.toUpperCase().replaceAll("[^a-zA-Z]", "").equals(EngineType.GAS.name())
                || engineType.toUpperCase().replaceAll("[^a-zA-Z]", "").equals(EngineType.HYBRID.name())
                || engineType.toUpperCase().replaceAll("[^a-zA-Z]", "").equals(EngineType.PETROL.name())
                || engineType.toUpperCase().replaceAll("[^a-zA-Z]", "").equals(EngineType.NONE.name())) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Engine Type", "must be one of the provided options (Diesel, Electric, Gas, Petrol, Hybrid, None"));
        }
    }

    protected Optional<CoreError> validatePlateNumber(AddVehicleRequest request) {
        String plateNumber = request.getPlateNumber();
        return (plateNumber == null || plateNumber.isEmpty())
                ? Optional.of(new CoreError("Plate Number", "cannot be empty"))
                : Optional.empty();
    }

    protected Optional<CoreError> validateTransmissionType(AddVehicleRequest request) {
        String transmissionType = request.getTransmissionType();
        if (transmissionType == null || transmissionType.isEmpty()) {
            return Optional.of(new CoreError("Transmission Type", "cannot be empty"));
        } else if (transmissionType.toUpperCase().replaceAll("[^a-zA-Z]", "").equals(TransmissionType.AUTOMATIC.name())
                || transmissionType.toUpperCase().replaceAll("[^a-zA-Z]", "").equals(TransmissionType.MANUAL.name())
                || transmissionType.toUpperCase().replaceAll("[^a-zA-Z]", "").equals(TransmissionType.NONE.name())) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Transmission Type", "must be one of the provided options (Automatic, Manual or None"));

        }
    }
}
