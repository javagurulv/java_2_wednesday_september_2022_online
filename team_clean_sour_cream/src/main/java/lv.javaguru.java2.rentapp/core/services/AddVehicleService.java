package lv.javaguru.java2.rentapp.core.services;

import static lv.javaguru.java2.rentapp.enums.VehicleType.CAR_TRAILER;
import static lv.javaguru.java2.rentapp.enums.VehicleType.MINIBUS;
import static lv.javaguru.java2.rentapp.enums.VehicleType.MOTORCYCLE;
import static lv.javaguru.java2.rentapp.enums.VehicleType.PASSENGER_CAR;

import java.util.HashMap;
import java.util.Map;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.AddVehicleResponse;
import lv.javaguru.java2.rentapp.domain.*;
import lv.javaguru.java2.rentapp.enums.VehicleType;

public class AddVehicleService {

    private Database database;
	private Map<VehicleType, VehicleTypeCreator> creatorMap;

	public AddVehicleService(Database database) {
		this.database = database;
		this.creatorMap = new HashMap<>();
		creatorMap.put(PASSENGER_CAR, new PassengerCarCreator(database));
		creatorMap.put(MINIBUS, new MiniBusCreator(database));
		creatorMap.put(MOTORCYCLE, new MotorcycleCreator(database));
		creatorMap.put(CAR_TRAILER, new CarTrailerCreator(database));
	}

	public AddVehicleResponse execute(AddVehicleRequest request) {
        VehicleType vehicleType = request.getVehicleType();
		VehicleTypeCreator creator = creatorMap.get(vehicleType);
		return creator.create(request);
    }

}
