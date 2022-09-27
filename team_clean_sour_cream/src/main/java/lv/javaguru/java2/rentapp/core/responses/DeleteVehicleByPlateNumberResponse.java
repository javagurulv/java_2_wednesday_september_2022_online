package lv.javaguru.java2.rentapp.core.responses;

import lombok.Getter;

import java.util.List;

@Getter
public class DeleteVehicleByPlateNumberResponse extends CoreResponse{

    private boolean isVehicleDeleted;

    public DeleteVehicleByPlateNumberResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteVehicleByPlateNumberResponse(boolean isVehicleDeleted) {
        this.isVehicleDeleted = isVehicleDeleted;
    }

}
