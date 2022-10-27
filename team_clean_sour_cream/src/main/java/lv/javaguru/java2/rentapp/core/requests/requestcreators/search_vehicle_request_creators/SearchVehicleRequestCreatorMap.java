package lv.javaguru.java2.rentapp.core.requests.requestcreators.search_vehicle_request_creators;

import java.util.HashMap;
import java.util.Map;

public class SearchVehicleRequestCreatorMap {

    private Map<Integer, SearchVehicleRequestCreator> requestCreatorMap;

    private SearchPassengerCarRequestCreator searchPassengerCarRequestCreator;

    public SearchVehicleRequestCreatorMap(SearchPassengerCarRequestCreator searchPassengerCarRequestCreator) {
        requestCreatorMap = new HashMap<>();
        requestCreatorMap.put(1, searchPassengerCarRequestCreator);
        requestCreatorMap.put(2, new SearchMiniBusRequestCreator());
        requestCreatorMap.put(3, new SearchMotorcycleRequestCreator());
        requestCreatorMap.put(4, new SearchCarTrailerRequestCreator());
    }

    public Integer getRequestCreatorMapSize() {
        return requestCreatorMap.size();
    }

    public SearchVehicleRequestCreator getSearchVehicleRequestCreator(int userChoice) {
        return requestCreatorMap.get(userChoice);
    }
}
