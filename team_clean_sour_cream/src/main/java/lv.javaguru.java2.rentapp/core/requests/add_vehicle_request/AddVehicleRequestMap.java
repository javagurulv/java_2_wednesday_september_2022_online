package lv.javaguru.java2.rentapp.core.requests.add_vehicle_request;

import java.util.HashMap;
import java.util.Map;

public class AddVehicleRequestMap {

    private Map<Integer, AddVehicleRequest> requestMap;

    public AddVehicleRequestMap() {
        requestMap = new HashMap<>();
        requestMap.put(1, new AddPassengerCarRequest())
    }
}
