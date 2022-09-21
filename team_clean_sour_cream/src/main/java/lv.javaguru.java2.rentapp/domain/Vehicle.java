package lv.javaguru.java2.rentapp.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.enums.Colour;
import lv.javaguru.java2.rentapp.enums.EngineType;
import lv.javaguru.java2.rentapp.enums.TransmissionType;

@Getter
@Setter
@EqualsAndHashCode
public abstract class Vehicle {

    private Long id;
    private String brand;
    private String model;
    private boolean isAvailableForRent;
    private Integer yearOfProduction;
    private Colour colour;
    private Double rentPricePerDay;
    private EngineType engineType;
    private String plateNumber;
    private TransmissionType transmissionType;

    public Vehicle(String brand, String model, boolean isAvailableForRent, Integer yearOfProduction, Colour colour,
                   Double rentPricePerDay, EngineType engineType, String plateNumber, TransmissionType transmissionType) {
        this.brand = brand;
        this.model = model;
        this.isAvailableForRent = isAvailableForRent;
        this.yearOfProduction = yearOfProduction;
        this.colour = colour;
        this.rentPricePerDay = rentPricePerDay;
        this.engineType = engineType;
        this.plateNumber = plateNumber;
        this.transmissionType = transmissionType;
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
