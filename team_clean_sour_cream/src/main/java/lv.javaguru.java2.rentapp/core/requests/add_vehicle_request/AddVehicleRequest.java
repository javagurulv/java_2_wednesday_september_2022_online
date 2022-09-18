package lv.javaguru.java2.rentapp.core.requests.add_vehicle_request;

import lombok.Getter;
import lv.javaguru.java2.rentapp.enums.Colour;
import lv.javaguru.java2.rentapp.enums.EngineType;
import lv.javaguru.java2.rentapp.enums.TransmissionType;

@Getter
public abstract class AddVehicleRequest {
    private String brand;
    private String model;
    private boolean isAvailableForRent;
    private int yearOfProduction;
    private Colour colour;
    private double rentPricePerDay;
    private EngineType engineType;
    private String plateNumber;
    private TransmissionType transmissionType;

    public AddVehicleRequest(String brand, String model, boolean isAvailableForRent, int yearOfProduction, String colour,
                             double rentPricePerDay, String engineType, String plateNumber, String transmissionType) {
        this.brand = brand;
        this.model = model;
        this.isAvailableForRent = isAvailableForRent;
        this.yearOfProduction = yearOfProduction;
        this.colour = Colour.valueOf(colour.toUpperCase().replaceAll("[^a-zA-Z]", ""));
        this.rentPricePerDay = rentPricePerDay;
        this.engineType = EngineType.valueOf(engineType.toUpperCase().replaceAll("[^a-zA-Z]", ""));
        this.plateNumber = plateNumber;
        this.transmissionType = TransmissionType.valueOf(transmissionType.toUpperCase().replaceAll("[^a-zA-Z]", ""));
    }
}
