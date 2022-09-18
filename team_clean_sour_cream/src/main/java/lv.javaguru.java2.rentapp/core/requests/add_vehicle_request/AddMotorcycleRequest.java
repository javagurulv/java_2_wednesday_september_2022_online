package lv.javaguru.java2.rentapp.core.requests.add_vehicle_request;

import lombok.Getter;

@Getter
public class AddMotorcycleRequest extends AddVehicleRequest{

    private int passengerAmount;

    public AddMotorcycleRequest(String brand, String model, boolean isAvailableForRent, int yearOfProduction,
                                String colour, double rentPricePerDay, String engineType, String plateNumber,
                                String transmissionType, int passengerAmount) {
        super(brand, model, isAvailableForRent, yearOfProduction, colour, rentPricePerDay, engineType, plateNumber, transmissionType);
        this.passengerAmount = passengerAmount;
    }
}
