package lv.javaguru.java2.rentapp.core.responses;

import lombok.Getter;
import lv.javaguru.java2.rentapp.domain.Vehicle;

import java.util.List;

@Getter
public class ShowAllVehiclesResponse extends CoreResponse {

    private List<Vehicle> vehicles;

    private String msg;

    public ShowAllVehiclesResponse(List<Vehicle> vehicles, List<CoreError> errors) {
        super(errors);
        this.vehicles = vehicles;
    }

    public ShowAllVehiclesResponse(String msg) {
        this.msg = msg;
    }

}
