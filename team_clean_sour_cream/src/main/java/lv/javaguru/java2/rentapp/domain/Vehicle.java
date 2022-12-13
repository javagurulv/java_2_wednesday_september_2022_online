package lv.javaguru.java2.rentapp.domain;

import lombok.Getter;
import lombok.Setter;
import lv.javaguru.java2.rentapp.enums.Colour;
import lv.javaguru.java2.rentapp.enums.EngineType;
import lv.javaguru.java2.rentapp.enums.TransmissionType;
import lv.javaguru.java2.rentapp.enums.VehicleType;

import java.util.Objects;

@Getter
@Setter
public abstract class Vehicle {

    public static final int MAX_ALLOWED_CURRENT_YEAR_BACKWARD_REDUCER = 100;
    private Long id;
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

    public Vehicle() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle vehicle)) return false;
        return isAvailableForRent() == vehicle.isAvailableForRent() && getVehicleType() == vehicle.getVehicleType() && getBrand().equals(vehicle.getBrand()) && getModel().equals(vehicle.getModel()) && getYearOfProduction().equals(vehicle.getYearOfProduction()) && getColour() == vehicle.getColour() && getRentPricePerDay().equals(vehicle.getRentPricePerDay()) && getEngineType() == vehicle.getEngineType() && getPlateNumber().equals(vehicle.getPlateNumber()) && getTransmissionType() == vehicle.getTransmissionType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVehicleType(), getBrand(), getModel(), isAvailableForRent(), getYearOfProduction(), getColour(), getRentPricePerDay(), getEngineType(), getPlateNumber(), getTransmissionType());
    }
}
