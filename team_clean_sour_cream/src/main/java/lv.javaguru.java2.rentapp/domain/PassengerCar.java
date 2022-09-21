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
public class PassengerCar extends Vehicle {

    private Integer passengerAmount;
    private Integer baggageAmount;
    private Integer doorsAmount;
    private boolean isAirConditioningAvailable;

    public PassengerCar(String brand, String model, boolean isAvailableForRent, Integer yearOfProduction, Colour colour,
                        Double rentPricePerDay, EngineType engineType, String plateNumber, TransmissionType transmissionType,
                        Integer passengerAmount, Integer baggageAmount, Integer doorsAmount, boolean isAirConditioningAvailable) {
        super(brand, model, isAvailableForRent, yearOfProduction, colour, rentPricePerDay, engineType, plateNumber, transmissionType);
        this.passengerAmount = passengerAmount;
        this.baggageAmount = baggageAmount;
        this.doorsAmount = doorsAmount;
        this.isAirConditioningAvailable = isAirConditioningAvailable;
    }

    @Override
    public String toString() {
        return "PassengerCar{" + super.toString() +
                ", passengerAmount=" + passengerAmount +
                ", baggageAmount=" + baggageAmount +
                ", doorsAmount=" + doorsAmount +
                ", isAirConditioningAvailable=" + isAirConditioningAvailable +
                "} ";
    }
}

