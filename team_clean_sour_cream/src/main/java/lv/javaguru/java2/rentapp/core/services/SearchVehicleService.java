package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.SearchVehicleResponse;

public class SearchVehicleService {

    private Database database;

    public SearchVehicleService(Database database) {
        this.database = database;
    }

    public SearchVehicleResponse execute(SearchVehicleRequest request) {
        return null;
    }
}
