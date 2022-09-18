package lv.javaguru.java2.rentapp.core.requests.add_vehicle_request;

import lombok.Getter;

@Getter
public class AddMiniBusRequest extends AddVehicleRequest{

    private int passengerAmount;
    private int baggageAmount;
    private int doorsAmount;
    private boolean isAirConditioningAvailable;

    public AddMiniBusRequest(String brand, String model, boolean isAvailableForRent, int yearOfProduction,
                                  String colour, double rentPricePerDay, String engineType, String plateNumber,
                                  String transmissionType, int passengerAmount, int baggageAmount,
                                  int doorsAmount, boolean isAirConditioningAvailable) {
        super(brand, model, isAvailableForRent, yearOfProduction, colour, rentPricePerDay, engineType, plateNumber, transmissionType);
        this.passengerAmount = passengerAmount;
        this.baggageAmount = baggageAmount;
        this.doorsAmount = doorsAmount;
        this.isAirConditioningAvailable = isAirConditioningAvailable;
    }
}
