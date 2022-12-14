package lv.javaguru.java2.rentapp.core.responses;

import lombok.Getter;
import lv.javaguru.java2.rentapp.domain.Vehicle;

import java.util.List;

@Getter
public class AddVehicleResponse extends CoreResponse {

    private Vehicle newVehicle;

    public AddVehicleResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddVehicleResponse(Vehicle newVehicle) {
        this.newVehicle = newVehicle;
    }
}
