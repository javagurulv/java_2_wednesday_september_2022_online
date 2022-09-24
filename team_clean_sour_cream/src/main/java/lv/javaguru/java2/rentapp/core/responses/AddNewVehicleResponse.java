package lv.javaguru.java2.rentapp.core.responses;

import lombok.Getter;
import lv.javaguru.java2.rentapp.domain.Vehicle;

import java.util.List;

@Getter
public class AddNewVehicleResponse extends CoreResponse {

    private Vehicle newVehicle;

    public AddNewVehicleResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddNewVehicleResponse(Vehicle newVehicle) {
        this.newVehicle = newVehicle;
    }

}
