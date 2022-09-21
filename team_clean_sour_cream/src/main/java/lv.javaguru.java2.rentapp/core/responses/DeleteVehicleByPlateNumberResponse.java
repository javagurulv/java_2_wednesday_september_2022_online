package lv.javaguru.java2.rentapp.core.responses;

import lombok.Getter;

@Getter
public class DeleteVehicleByPlateNumberResponse extends CoreResponse{

    private boolean isVehicleDeleted;

    public DeleteVehicleByPlateNumberResponse(boolean isVehicleDeleted) {
        this.isVehicleDeleted = isVehicleDeleted;
    }

}
