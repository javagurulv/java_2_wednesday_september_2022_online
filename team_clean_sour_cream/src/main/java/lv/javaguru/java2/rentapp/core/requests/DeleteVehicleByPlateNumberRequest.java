package lv.javaguru.java2.rentapp.core.requests;

import lombok.Getter;

@Getter
public class DeleteVehicleByPlateNumberRequest extends Request{

    private String plateNumber;

    public DeleteVehicleByPlateNumberRequest(String plateNumber) {
        this.plateNumber = plateNumber;
    }
}
