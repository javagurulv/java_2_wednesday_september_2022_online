package lv.javaguru.java2.rentapp.core.requests.add_vehicle_request;

import lombok.Getter;

@Getter
public class AddCarTrailerRequest extends AddVehicleRequest{

    private int deckWidthInCm;
    private int deckLengthInCm;
    private int deckHeightInCm;
    private int emptyWeightInKg;
    private int maxLoadWeightInKg;

    public AddCarTrailerRequest(String brand, String model, boolean isAvailableForRent, int yearOfProduction,
                                String colour, double rentPricePerDay, String engineType, String plateNumber,
                                String transmissionType, int deckWidthInCm, int deckLengthInCm, int deckHeightInCm,
                                int emptyWeightInKg, int maxLoadWeightInKg) {
        super(brand, model, isAvailableForRent, yearOfProduction, colour, rentPricePerDay, engineType, plateNumber, transmissionType);
        this.deckWidthInCm = deckWidthInCm;
        this.deckLengthInCm = deckLengthInCm;
        this.deckHeightInCm = deckHeightInCm;
        this.emptyWeightInKg = emptyWeightInKg;
        this.maxLoadWeightInKg = maxLoadWeightInKg;
    }
}
