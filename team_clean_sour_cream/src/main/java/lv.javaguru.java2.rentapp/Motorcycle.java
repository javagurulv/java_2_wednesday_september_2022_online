package lv.javaguru.java2.rentapp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
class Motorcycle extends Vehicle {

    private int passengerAmount;

    public Motorcycle(String brand, String model, boolean isAvailableForRent, int yearOfProduction, String colour, double rentPricePerDay, String engineType, String plateNumber, String transmissionType, int passengerAmount) {
        super(brand, model, isAvailableForRent, yearOfProduction, colour, rentPricePerDay, engineType, plateNumber, transmissionType);
        this.passengerAmount = passengerAmount;
    }

    @Override
    public String toString() {
        return "Motorcycle{" + super.toString() +
                ", passengerAmount=" + passengerAmount +
                "} ";
    }
}
