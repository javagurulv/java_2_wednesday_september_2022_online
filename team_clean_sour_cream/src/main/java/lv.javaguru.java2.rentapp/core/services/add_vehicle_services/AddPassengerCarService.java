package lv.javaguru.java2.rentapp.core.services.add_vehicle_services;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.AddVehicleResponse;
import lv.javaguru.java2.rentapp.domain.PassengerCar;
import lv.javaguru.java2.rentapp.domain.Vehicle;

public class AddPassengerCarService extends AddNewVehicleService{

    public AddPassengerCarService(Database database) {
        super(database);
    }

    @Override
    public AddVehicleResponse execute(AddVehicleRequest request) {
        Vehicle passengerCar = new PassengerCar(request.getBrand(), request.getModel(), )
        return null;
    }
}
