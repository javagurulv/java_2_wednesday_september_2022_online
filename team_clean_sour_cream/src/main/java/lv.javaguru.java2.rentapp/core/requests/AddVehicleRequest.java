package lv.javaguru.java2.rentapp.core.requests;

import lombok.Builder;
import lombok.Getter;
import lv.javaguru.java2.rentapp.enums.Colour;
import lv.javaguru.java2.rentapp.enums.EngineType;
import lv.javaguru.java2.rentapp.enums.TransmissionType;

@Getter
@Builder
public class AddVehicleRequest {

    private String brand;
    private String model;
    private boolean isAvailableForRent;
    private int yearOfProduction;
    private Colour colour;
    private double rentPricePerDay;
    private EngineType engineType;
    private String plateNumber;
    private TransmissionType transmissionType;

    private int passengerAmount;
    private int baggageAmount;
    private int doorsAmount;
    private boolean isAirConditioningAvailable;

    private int deckWidthInCm;
    private int deckLengthInCm;
    private int deckHeightInCm;
    private int emptyWeightInKg;
    private int maxLoadWeightInKg;

    //Конструктор для легковой и минибуса
    public AddVehicleRequest(String brand, String model, boolean isAvailableForRent, int yearOfProduction, String colour,
                             double rentPricePerDay, String engineType, String plateNumber,
                             String transmissionType, int passengerAmount, int baggageAmount, int doorsAmount,
                             boolean isAirConditioningAvailable) {
        this.brand = brand;
        this.model = model;
        this.isAvailableForRent = isAvailableForRent;
        this.yearOfProduction = yearOfProduction;
        this.colour = Colour.valueOf(colour.toUpperCase().replaceAll("[^a-zA-Z]", ""));
        this.rentPricePerDay = rentPricePerDay;
        this.engineType = EngineType.valueOf(engineType.toUpperCase().replaceAll("[^a-zA-Z]", ""));
        this.plateNumber = plateNumber;
        this.transmissionType = TransmissionType.valueOf(transmissionType.toUpperCase().replaceAll("[^a-zA-Z]", ""));
        this.passengerAmount = passengerAmount;
        this.baggageAmount = baggageAmount;
        this.doorsAmount = doorsAmount;
        this.isAirConditioningAvailable = isAirConditioningAvailable;
    }

    //Конструктор для мото
    public AddVehicleRequest(String brand, String model, boolean isAvailableForRent, int yearOfProduction, String colour,
                             double rentPricePerDay, String engineType, String plateNumber,
                             String transmissionType, int passengerAmount) {
        this.brand = brand;
        this.model = model;
        this.isAvailableForRent = isAvailableForRent;
        this.yearOfProduction = yearOfProduction;
        this.colour = Colour.valueOf(colour.toUpperCase().replaceAll("[^a-zA-Z]", ""));
        this.rentPricePerDay = rentPricePerDay;
        this.engineType = EngineType.valueOf(engineType.toUpperCase().replaceAll("[^a-zA-Z]", ""));
        this.plateNumber = plateNumber;
        this.transmissionType = TransmissionType.valueOf(transmissionType.toUpperCase().replaceAll("[^a-zA-Z]", ""));
        this.passengerAmount = passengerAmount;
    }

    //Конструктор для прицепа
    public AddVehicleRequest(String brand, String model, boolean isAvailableForRent, int yearOfProduction, String colour,
                             double rentPricePerDay, String engineType, String plateNumber, String transmissionType,
                             int deckWidthInCm, int deckLengthInCm, int deckHeightInCm, int emptyWeightInKg, int maxLoadWeightInKg) {
        this.brand = brand;
        this.model = model;
        this.isAvailableForRent = isAvailableForRent;
        this.yearOfProduction = yearOfProduction;
        this.colour = Colour.valueOf(colour.toUpperCase().replaceAll("[^a-zA-Z]", ""));
        this.rentPricePerDay = rentPricePerDay;
        this.engineType = EngineType.valueOf(engineType.toUpperCase().replaceAll("[^a-zA-Z]", ""));
        this.plateNumber = plateNumber;
        this.transmissionType = TransmissionType.valueOf(transmissionType.toUpperCase().replaceAll("[^a-zA-Z]", ""));
        this.deckWidthInCm = deckWidthInCm;
        this.deckLengthInCm = deckLengthInCm;
        this.deckHeightInCm = deckHeightInCm;
        this.emptyWeightInKg = emptyWeightInKg;
        this.maxLoadWeightInKg = maxLoadWeightInKg;
    }
}
