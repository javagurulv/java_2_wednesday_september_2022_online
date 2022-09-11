package lv.javaguru.java2.rentapp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class MiniBus extends Vehicle {

    private int passengerAmount;
    private int baggageAmount;
    private int doorsAmount;
    private boolean isAirConditioningAvailable;

    public MiniBus(String brand, String model, boolean isAvailableForRent, int yearOfProduction, String colour, double rentPricePerDay, String engineType, String plateNumber, String transmissionType, int passengerAmount, int baggageAmount, int doorsAmount, boolean isAirConditioningAvailable) {
        super(brand, model, isAvailableForRent, yearOfProduction, colour, rentPricePerDay, engineType, plateNumber, transmissionType);
        this.passengerAmount = passengerAmount;
        this.baggageAmount = baggageAmount;
        this.doorsAmount = doorsAmount;
        this.isAirConditioningAvailable = isAirConditioningAvailable;
    }

    @Override
    public String toString() {
        return "MiniBus{" + super.toString() +
                ", passengerAmount=" + passengerAmount +
                ", baggageAmount=" + baggageAmount +
                ", doorsAmount=" + doorsAmount +
                ", isAirConditioningAvailable=" + isAirConditioningAvailable +
                "} ";
    }
}
