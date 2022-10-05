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
public class MiniBus extends Vehicle {

    public static final int BUS_MAX_DOORS_AMOUNT = 6;
    public static final int BUS_MIN_DOORS_AMOUNT = 2;
    public static final int BUS_MAX_PASSENGER_AMOUNT = 20;
    public static final int BUS_MAX_BAGGAGE_AMOUNT = 30;
    private Integer passengerAmount;
    private Integer baggageAmount;
    private Integer doorsAmount;
    private boolean isAirConditioningAvailable;

    public MiniBus(String brand, String model, boolean isAvailableForRent, Integer yearOfProduction,
                   Colour colour, Double rentPricePerDay, EngineType engineType, String plateNumber,
                   TransmissionType transmissionType, Integer passengerAmount, Integer baggageAmount, Integer doorsAmount, boolean isAirConditioningAvailable) {
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
