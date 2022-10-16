package lv.javaguru.java2.rentapp.core.responses;


import java.util.List;

public class RentVehicleResponse extends CoreResponse {

    String message;

    public RentVehicleResponse(List<CoreError> errors) {
        super(errors);
    }

    public RentVehicleResponse(String message) {
        this.message = message;
    }
}
