package lv.javaguru.java2.rentapp.core.responses;

import lombok.Getter;
import lv.javaguru.java2.rentapp.domain.Vehicle;

import java.util.List;

@Getter
public class ShowAllVehiclesResponse extends CoreResponse{

    private List<Vehicle> vehicles;

    public ShowAllVehiclesResponse(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
