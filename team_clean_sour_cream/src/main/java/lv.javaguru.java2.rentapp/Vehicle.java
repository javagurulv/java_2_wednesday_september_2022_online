package lv.javaguru.java2.rentapp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString

public abstract class Vehicle {

    private Long id;
    private String brand;
    private String model;
    private boolean isAvailable;
    private int yearOfProduction;
    private String color;
    private double pricePerDay;
    private String engineType;
    private String plateNumber;
    private String transmissionType;

    public Vehicle(String brand, String model, boolean isAvailable, int yearOfProduction, String color,
                   double pricePerDay, String engineType, String plateNumber, String transmissionType) {
        this.brand = brand;
        this.model = model;
        this.isAvailable = isAvailable;
        this.yearOfProduction = yearOfProduction;
        this.color = color;
        this.pricePerDay = pricePerDay;
        this.engineType = engineType;
        this.plateNumber = plateNumber;
        this.transmissionType = transmissionType;
    }
}
