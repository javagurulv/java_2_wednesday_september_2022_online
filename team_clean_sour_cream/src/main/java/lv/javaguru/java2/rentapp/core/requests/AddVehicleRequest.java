package lv.javaguru.java2.rentapp.core.requests;

import lombok.Builder;
import lombok.Getter;

import lv.javaguru.java2.rentapp.enums.VehicleType;

@Getter
@Builder
public class AddVehicleRequest extends Request{

    private VehicleType vehicleType;

    private String brand;
    private String model;
    private boolean isAvailableForRent;
    private Integer yearOfProduction;
    private String colour;
    private Double rentPricePerDay;
    private String engineType;
    private String plateNumber;
    private String transmissionType;

    private Integer passengerAmount;
    private Integer baggageAmount;
    private Integer doorsAmount;
    private String isAirConditioningAvailable;

    private Integer deckWidthInCm;
    private Integer deckLengthInCm;
    private Integer deckHeightInCm;
    private Integer emptyWeightInKg;
    private Integer maxLoadWeightInKg;

}
