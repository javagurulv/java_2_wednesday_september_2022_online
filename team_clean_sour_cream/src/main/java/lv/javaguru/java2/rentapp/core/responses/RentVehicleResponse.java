package lv.javaguru.java2.rentapp.core.responses;


import lombok.Getter;

import java.util.List;

@Getter
public class RentVehicleResponse extends CoreResponse {

    String message;

    public RentVehicleResponse(List<CoreError> errors) {
        super(errors);
    }

    public RentVehicleResponse(String message) {
        this.message = message;
    }
}
