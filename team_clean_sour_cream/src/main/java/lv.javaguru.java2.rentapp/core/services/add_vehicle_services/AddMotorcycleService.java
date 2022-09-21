package lv.javaguru.java2.rentapp.core.services.add_vehicle_services;


import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.AddVehicleResponse;
import lv.javaguru.java2.rentapp.domain.Motorcycle;
import lv.javaguru.java2.rentapp.domain.Vehicle;

public class AddMotorcycleService extends AddNewVehicleService{

    public AddMotorcycleService(Database database) {
        super(database);
    }

    @Override
    public AddVehicleResponse execute(AddVehicleRequest request) {
        int yearOfProduction = Integer.parseInt(request.getYearOfProduction());
        Vehicle motorcycle = new Motorcycle(request.getBrand(), request.getModel(), request.isAvailableForRent(),
                yearOfProduction, request.getColour(), request.getRentPricePerDay(), request.getEngineType(),
                request.getPlateNumber(), request.getTransmissionType(), request.getPassengerAmount());
        database.addNewVehicle(motorcycle);
        return new AddVehicleResponse(motorcycle);
    }
}
