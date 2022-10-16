package lv.javaguru.java2.rentapp.core.responses;

import lombok.Getter;
import lv.javaguru.java2.rentapp.domain.Vehicle;

import java.util.List;

@Getter
public class FindAvailableVehiclesResponse extends CoreResponse{

    private List<Vehicle> vehicles;

    public FindAvailableVehiclesResponse(List<CoreError> errors, List<Vehicle> vehicles) {
        super(errors);
        this.vehicles = vehicles;
    }
}
