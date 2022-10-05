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
    private String hasConditioner;
    private String transmissionType;
    private Integer deckWidthInCm;
    private Integer deckLengthInCm;
    private Integer deckHeightInCm;
    private Integer emptyWeightInKg;
    private Integer maxLoadWeightInKg;

    private Ordering ordering;

}
