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

    private int deckWidthInCm;
    private int deckLengthInCm;
    private int deckHeightInCm;
    private int emptyWeightInKg;
    private int maxLoadWeightInKg;

    public CarTrailer(String brand, String model, boolean isAvailable, int yearOfProduction, Colour colour,
                      double rentPricePerDay, EngineType engineType, String plateNumber, TransmissionType transmissionType,
                      int deckWidthInCm, int deckLengthInCm, int deckHeightInCm, int emptyWeightInKg, int maxLoadWeightInKg) {
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
