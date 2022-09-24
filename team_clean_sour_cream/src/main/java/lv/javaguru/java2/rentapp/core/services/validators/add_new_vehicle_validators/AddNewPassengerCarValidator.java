package lv.javaguru.java2.rentapp.core.services.validators.add_new_vehicle_validators;

import lv.javaguru.java2.rentapp.core.requests.AddNewVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//not all fields validated yet....in progress
public class AddNewPassengerCarValidator implements AddNewVehicleValidator {

    public List<CoreError> validate(AddNewVehicleRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateBrand(request).ifPresent(errors::add);
        validateModel(request).ifPresent(errors::add);
        validateYearOfProduction(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateBrand(AddNewVehicleRequest request) {
        String brand = request.getBrand();
        return (brand == null || brand.isEmpty())
                ? Optional.of(new CoreError("Brand", "cannot be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateModel(AddNewVehicleRequest request) {
        String model = request.getModel();
        return (model == null || model.isEmpty())
                ? Optional.of(new CoreError("Model", "cannot be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateYearOfProduction(AddNewVehicleRequest request) {
        Integer yearOfProduction = request.getYearOfProduction();
        return (yearOfProduction == null)
                ? Optional.of(new CoreError("YearOfProduction", "cannot be empty"))
                : Optional.empty();
    }

}
