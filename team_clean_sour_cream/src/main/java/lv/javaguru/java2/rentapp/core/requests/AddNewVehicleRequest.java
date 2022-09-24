package lv.javaguru.java2.rentapp.core.requests;

import lombok.Builder;
import lombok.Getter;
import lv.javaguru.java2.rentapp.enums.Colour;
import lv.javaguru.java2.rentapp.enums.EngineType;
import lv.javaguru.java2.rentapp.enums.TransmissionType;
import lv.javaguru.java2.rentapp.enums.VehicleType;

@Getter
@Builder
public class AddNewVehicleRequest {

    private VehicleType vehicleType;

    private String brand;
    private String model;
    private boolean isAvailableForRent;
    private Integer yearOfProduction;
    private Colour colour;
    private Double rentPricePerDay;
    private EngineType engineType;
    private String plateNumber;
    private TransmissionType transmissionType;

    private Integer passengerAmount;
    private Integer baggageAmount;
    private Integer doorsAmount;
    private boolean isAirConditioningAvailable;

    private Integer deckWidthInCm;
    private Integer deckLengthInCm;
    private Integer deckHeightInCm;
    private Integer emptyWeightInKg;
    private Integer maxLoadWeightInKg;

}
