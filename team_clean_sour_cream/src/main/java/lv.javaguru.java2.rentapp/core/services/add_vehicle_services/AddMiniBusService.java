package lv.javaguru.java2.rentapp.core.services.add_vehicle_services;


import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.AddVehicleResponse;
import lv.javaguru.java2.rentapp.domain.MiniBus;
import lv.javaguru.java2.rentapp.domain.Vehicle;

public class AddMiniBusService extends AddNewVehicleService{

    public AddMiniBusService(Database database) {
        super(database);
    }

    @Override
    public AddVehicleResponse execute(AddVehicleRequest request) {
        int yearOfProduction = Integer.parseInt(request.getYearOfProduction());
        Vehicle miniBus = new MiniBus(request.getBrand(), request.getModel(), request.isAvailableForRent(),
                yearOfProduction, request.getColour(), request.getRentPricePerDay(), request.getEngineType(),
                request.getPlateNumber(), request.getTransmissionType(), request.getPassengerAmount(), request.getBaggageAmount(),
                request.getDoorsAmount(), request.isAirConditioningAvailable());
        database.addNewVehicle(miniBus);
        return new AddVehicleResponse(miniBus);
    }
}
