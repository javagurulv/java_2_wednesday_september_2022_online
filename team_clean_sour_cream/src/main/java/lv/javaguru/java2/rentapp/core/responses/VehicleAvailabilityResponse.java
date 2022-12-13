package lv.javaguru.java2.rentapp.core.responses;

import lombok.Getter;
import lv.javaguru.java2.rentapp.domain.Vehicle;

import java.util.List;

@Getter
public class VehicleAvailabilityResponse extends CoreResponse{

    private List<Vehicle> vehicles;
    private List<Vehicle> vehiclesPaged;

    public VehicleAvailabilityResponse(List<CoreError> errors, List<Vehicle> vehicles) {
        super(errors);
        this.vehicles = vehicles;
    }

    public void setVehiclesPaged(List<Vehicle> vehiclesPaged) {
        this.vehiclesPaged = vehiclesPaged;
    }
}
