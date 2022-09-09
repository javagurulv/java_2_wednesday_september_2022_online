package lv.javaguru.java2.rentapp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public abstract class Vehicle {

    private Long id;
    private String brand;
    private String model;
    private boolean isAvailableForRent;
    private int yearOfProduction;
    private Colour colour;
    private double rentPricePerDay;
    private EngineType engineType;
    private String plateNumber;
    private TransmissionType transmissionType;

    public Vehicle(String brand, String model, boolean isAvailableForRent, int yearOfProduction, String colour,
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

    @Override
    public String toString() {
        return "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", isAvailable=" + isAvailableForRent +
                ", yearOfProduction=" + yearOfProduction +
                ", colour=" + colour.getNameColour() +
                ", pricePerDay=" + rentPricePerDay +
                ", engineType='" + engineType.getNameEngineType() + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", transmissionType='" + transmissionType.getNameTransmissionType() + '\'';
    }
}
