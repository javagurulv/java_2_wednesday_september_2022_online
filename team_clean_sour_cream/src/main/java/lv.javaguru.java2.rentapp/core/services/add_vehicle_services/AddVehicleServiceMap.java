package lv.javaguru.java2.rentapp.core.services.add_vehicle_services;

import lv.javaguru.java2.rentapp.core.database.Database;

import java.util.HashMap;
import java.util.Map;

public class AddVehicleServiceMap {

    private Map<Integer, AddNewVehicleService> serviceMap;

    public AddVehicleServiceMap(Database database) {
        serviceMap = new HashMap<>();
        serviceMap.put(1, new AddPassengerCarService(database));
        serviceMap.put(2, new AddMiniBusService(database));
        serviceMap.put(3, new AddMotorcycleService(database));
        serviceMap.put(4, new AddCarTrailerService(database));
    }

    public AddNewVehicleService getService(int userChoice) {
        return serviceMap.get(userChoice);
    }
}
