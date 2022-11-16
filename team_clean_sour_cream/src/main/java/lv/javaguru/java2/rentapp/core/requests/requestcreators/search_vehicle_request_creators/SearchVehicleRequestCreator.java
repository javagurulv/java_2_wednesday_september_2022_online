package lv.javaguru.java2.rentapp.core.requests.requestcreators.search_vehicle_request_creators;

import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;

public interface SearchVehicleRequestCreator {

    SearchVehicleRequest createRequest();
    void setPagingEnabled(boolean pagingEnabled);

}
