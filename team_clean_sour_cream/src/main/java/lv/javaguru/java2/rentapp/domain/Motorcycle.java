package lv.javaguru.java2.rentapp.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lv.javaguru.java2.rentapp.enums.Colour;
import lv.javaguru.java2.rentapp.enums.EngineType;
import lv.javaguru.java2.rentapp.enums.TransmissionType;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Motorcycle extends Vehicle {

    public static final int MOTO_MAX_PASSENGER_AMOUNT = 4;
    public static final int MOTO_MIN_PASSENGER_AMOUNT = 1;
    private Integer passengerAmount;

    public Motorcycle() {
    }

    public Motorcycle(String brand, String model, boolean isAvailableForRent, Integer yearOfProduction, Colour colour,
                      Double rentPricePerDay, EngineType engineType, String plateNumber, TransmissionType transmissionType, Integer passengerAmount) {
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
