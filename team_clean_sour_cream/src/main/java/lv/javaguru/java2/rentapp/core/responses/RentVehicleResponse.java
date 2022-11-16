package lv.javaguru.java2.rentapp.core.responses;


import lombok.Getter;
import lv.javaguru.java2.rentapp.domain.RentDeal;

import java.util.List;

@Getter
public class RentVehicleResponse extends CoreResponse {

    RentDeal newRentDeal;
    String message;

    public RentVehicleResponse(String message) {
        this.message = message;
    }

    public RentVehicleResponse(List<CoreError> errors) {
        super(errors);
    }

    public RentVehicleResponse(RentDeal newRentDeal) {
        this.newRentDeal = newRentDeal;
    }
}
