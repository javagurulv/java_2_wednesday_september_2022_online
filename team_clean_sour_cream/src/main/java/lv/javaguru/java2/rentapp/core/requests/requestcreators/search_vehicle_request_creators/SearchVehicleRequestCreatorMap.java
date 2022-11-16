package lv.javaguru.java2.rentapp.core.requests.requestcreators.search_vehicle_request_creators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SearchVehicleRequestCreatorMap {

    private Map<Integer, SearchVehicleRequestCreator> requestCreatorMap;

    @Autowired
    public SearchVehicleRequestCreatorMap(SearchPassengerCarRequestCreator searchPassengerCarRequestCreator
            , SearchMiniBusRequestCreator searchMiniBusRequestCreator
            , SearchMotorcycleRequestCreator searchMotorcycleRequestCreator
            , SearchCarTrailerRequestCreator searchCarTrailerRequestCreator) {
        requestCreatorMap = new HashMap<>();
        requestCreatorMap.put(1, searchPassengerCarRequestCreator);
        requestCreatorMap.put(2, searchMiniBusRequestCreator);
        requestCreatorMap.put(3, searchMotorcycleRequestCreator);
        requestCreatorMap.put(4, searchCarTrailerRequestCreator);
    }

    public Integer getRequestCreatorMapSize() {
        return requestCreatorMap.size();
    }

    public SearchVehicleRequestCreator getSearchVehicleRequestCreator(int userChoice) {
        return requestCreatorMap.get(userChoice);
    }
}
