package lv.javaguru.java2.rentapp;

public class Car extends Vehicle {

    public Car(String brand, String model, boolean isAvailable, int yearOfProduction, String color,
               double pricePerDay, String engineType, String plateNumber, String transmissionType) {
        super(brand, model, isAvailable, yearOfProduction, color,
                pricePerDay, engineType, plateNumber, transmissionType);
    }
}
