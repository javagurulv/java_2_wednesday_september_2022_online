package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.VehicleDatabase;
import lv.javaguru.java2.rentapp.core.requests.Paging;
import lv.javaguru.java2.rentapp.core.requests.ShowAllVehiclesRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.responses.ShowAllVehiclesResponse;
import lv.javaguru.java2.rentapp.core.services.validators.PagingValidator;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShowAllVehiclesService {

    @Autowired private VehicleDatabase vehicleDatabase;
    @Autowired private PagingValidator pagingValidator;

    public ShowAllVehiclesResponse execute(ShowAllVehiclesRequest request) {

        if (request.getPaging() != null) {
            List<CoreError> errors = pagingValidator.validate(request.getPaging());
            if (!errors.isEmpty()) {
                return new ShowAllVehiclesResponse(null, errors);
            }
        }

        List<Vehicle> vehicles = vehicleDatabase.getAllVehicles();

        vehicles = paging(vehicles, request.getPaging());

        if (vehicles.isEmpty()) {
            return new ShowAllVehiclesResponse("There is no vehicles in Data Base");
        }
        return new ShowAllVehiclesResponse(vehicles, null);
    }

    private List<Vehicle> paging(List<Vehicle> vehicles, Paging paging) {
        if (paging != null) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return vehicles.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return vehicles;
        }
    }
}
