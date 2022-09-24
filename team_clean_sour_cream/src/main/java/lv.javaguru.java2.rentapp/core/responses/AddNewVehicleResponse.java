package lv.javaguru.java2.rentapp.core.responses;

import lombok.Getter;
import lv.javaguru.java2.rentapp.domain.Vehicle;

@Getter
public class AddNewVehicleResponse extends CoreResponse{

    private Vehicle newVehicle;

    public AddNewVehicleResponse(Vehicle newVehicle) {
        this.newVehicle = newVehicle;
    }

}
