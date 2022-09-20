package lv.javaguru.java2.rentapp.core.services.validators;

import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddPassengerCarValidator {

    public List<CoreError> validate (AddVehicleRequest request) {
        List<CoreError> errors = new ArrayList<>();
        return null;
    }
    private Optional<CoreError> validateBrand (AddVehicleRequest request) {
        String brand = request.getBrand();
        return(brand == null || brand.isEmpty())
                ? Optional.of(new CoreError("Brand" , "cannot be empty"))
                : Optional.empty();
    }
    private Optional<CoreError> validateModel (AddVehicleRequest request) {
        String model = request.getModel();
        return (model == null || model.isEmpty())
                ? Optional.of(new CoreError("Model", "cannot be empty"))
                : Optional.empty();
    }
    private Optional<CoreError> validateYearOfProduction (AddVehicleRequest request) {
        String yearOfProduction = request.getYearOfProduction();
        int yearOfProductionInt = NumberUtils.toInt(yearOfProduction , 0);
        return (yearOfProductionInt == 0)
                ? Optional.of(new CoreError("YearOfProduction", "cannot be empty"))
                : Optional.empty();
    }

}
