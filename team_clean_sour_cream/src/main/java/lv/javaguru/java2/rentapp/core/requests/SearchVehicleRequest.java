package lv.javaguru.java2.rentapp.core.requests;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchVehicleRequest {

    private String vehicleType;
    private Integer doorsAmount;
    private Integer baggageAmount;
    private Integer passengerAmount;
    private boolean hasConditioner;
    private String transmissionType;

}
