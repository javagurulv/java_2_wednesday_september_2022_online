package lv.javaguru.java2.rentapp.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lv.javaguru.java2.rentapp.enums.Colour;
import lv.javaguru.java2.rentapp.enums.EngineType;
import lv.javaguru.java2.rentapp.enums.TransmissionType;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CarTrailer extends Vehicle {

    public static final int MAX_DECK_WIDTH_IN_CM = 500;
    public static final int MIN_DECK_WIDTH_IN_CM = 100;
    public static final int MAX_DECK_LENGTH_IN_CM = 1000;
    public static final int MIN_DECK_LENGTH_IN_CM = 100;
    public static final int MAX_DECK_HEIGHT_IN_CM = 300;
    public static final int MIN_DECK_HEIGHT_IN_CM = 50;
    public static final int MAX_EMPTY_WEIGHT_IN_KG = 2000;
    public static final int MIN_EMPTY_WEIGHT_IN_KG = 200;
    public static final int MAX_LOAD_WEIGHT_IN_KG = 5000;
    public static final int MIN_LOAD_WEIGHT_IN_KG = 200;
    private Integer deckWidthInCm;
    private Integer deckLengthInCm;
    private Integer deckHeightInCm;
    private Integer emptyWeightInKg;
    private Integer maxLoadWeightInKg;

    public CarTrailer(String brand, String model, boolean isAvailable, Integer yearOfProduction, Colour colour,
                      Double rentPricePerDay, EngineType engineType, String plateNumber, TransmissionType transmissionType,
                      Integer deckWidthInCm, Integer deckLengthInCm, Integer deckHeightInCm, Integer emptyWeightInKg, Integer maxLoadWeightInKg) {
        super(brand, model, isAvailable, yearOfProduction, colour, rentPricePerDay, engineType, plateNumber, transmissionType);
        this.deckWidthInCm = deckWidthInCm;
        this.deckLengthInCm = deckLengthInCm;
        this.deckHeightInCm = deckHeightInCm;
        this.emptyWeightInKg = emptyWeightInKg;
        this.maxLoadWeightInKg = maxLoadWeightInKg;
    }

    @Override
    public String toString() {
        return "CarTrailer{" + super.toString() +
                ", deckWidthInCm=" + deckWidthInCm +
                ", deckLengthInCm=" + deckLengthInCm +
                ", deckHeightInCm=" + deckHeightInCm +
                ", emptyWeightInKg=" + emptyWeightInKg +
                ", maxLoadWeightInKg=" + maxLoadWeightInKg +
                "} ";
    }
}
