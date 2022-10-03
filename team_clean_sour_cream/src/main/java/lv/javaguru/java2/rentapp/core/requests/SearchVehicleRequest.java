package lv.javaguru.java2.rentapp.core.requests;

import lombok.Builder;
import lombok.Getter;
import lv.javaguru.java2.rentapp.enums.VehicleType;

@Getter
@Builder
public class SearchVehicleRequest {

    private VehicleType vehicleType;
    private Integer doorsAmount;
    private Integer baggageAmount;
    private Integer passengerAmount;
    private String hasConditioner;
    private String transmissionType;
    private Integer deckWidthInCm;
    private Integer deckLengthInCm;
    private Integer deckHeightInCm;
    private Integer emptyWeightInKg;
    private Integer maxLoadWeightInKg;

}
