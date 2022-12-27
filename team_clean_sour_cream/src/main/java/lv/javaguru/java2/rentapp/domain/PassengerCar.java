package lv.javaguru.java2.rentapp.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lv.javaguru.java2.rentapp.enums.Colour;
import lv.javaguru.java2.rentapp.enums.EngineType;
import lv.javaguru.java2.rentapp.enums.TransmissionType;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class PassengerCar extends Vehicle {
    @Value("${car.max.passenger.amount}")
    public static final int CAR_MAX_PASSENGER_AMOUNT = 7;
    @Value("${car.min.passenger.amount}")
    public static final int CAR_MIN_PASSENGER_AMOUNT = 1;
    @Value("${car.max.baggage.amount}")
    public static final int CAR_MAX_BAGGAGE_AMOUNT = 10;
    @Value("${car.max.doors.amount}")
    public static final int CAR_MAX_DOORS_AMOUNT = 5;
    @Value("${car.mix.doors.amount}")
    public static final int CAR_MIN_DOORS_AMOUNT = 2;
    private Integer passengerAmount;
    private Integer baggageAmount;
    private Integer doorsAmount;
    private boolean isAirConditioningAvailable;

    public PassengerCar() {
    }

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

