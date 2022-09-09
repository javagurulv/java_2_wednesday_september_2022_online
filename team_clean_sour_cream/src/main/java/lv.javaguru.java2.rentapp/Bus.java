package lv.javaguru.java2.rentapp;

public class Bus extends Vehicle {

    public Bus(String brand, String model, boolean isAvailable, int yearOfProduction, String color, double pricePerDay, String engineType, String plateNumber, String transmissionType) {
        super(brand, model, isAvailable, yearOfProduction, color, pricePerDay, engineType, plateNumber, transmissionType);
    }
}
