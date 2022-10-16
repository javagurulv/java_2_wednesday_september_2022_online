package lv.javaguru.java2.rentapp.core.responses;

import lombok.Getter;
import lv.javaguru.java2.rentapp.domain.Vehicle;

import java.util.List;

@Getter
public class SearchVehicleResponse extends CoreResponse {
    private List<Vehicle> vehicleList;

    public SearchVehicleResponse(List<Vehicle> vehicleList, List<CoreError> errors) {
        super(errors);
        this.vehicleList = vehicleList;
    }
}
