package lv.javaguru.java2.rentapp.core.responses;

import lombok.Getter;

import java.util.List;

@Getter
public class DeleteVehicleByPlateNumberResponse extends CoreResponse {

    private String vehicleDeletedMsg;

    public DeleteVehicleByPlateNumberResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteVehicleByPlateNumberResponse(String vehicleDeletedMsg) {
        this.vehicleDeletedMsg = vehicleDeletedMsg;
    }

}
