package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.DealDatabase;
import lv.javaguru.java2.rentapp.core.database.VehicleDatabase;
import lv.javaguru.java2.rentapp.core.requests.GeneralRentVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.responses.RentVehicleResponse;
import lv.javaguru.java2.rentapp.core.services.validators.RentVehicleValidator;
import lv.javaguru.java2.rentapp.domain.Client;
import lv.javaguru.java2.rentapp.domain.RentDeal;
import lv.javaguru.java2.rentapp.domain.RentDealFactory;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Component
public class RentVehicleService {
    @Autowired
    private DealDatabase dealDatabase;
    @Autowired
    private VehicleDatabase vehicleDatabase;
    @Autowired
    private RentVehicleValidator validator;

    public RentVehicleResponse execute(GeneralRentVehicleRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new RentVehicleResponse(errors);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate startDate = LocalDate.parse(request.getRentStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(request.getRentEndDate(), formatter);

        Client client = getClient(request);
        Optional<Vehicle> vehicleOpt = vehicleDatabase.getById(request.getVehicleId());
        if (vehicleOpt.isPresent()) {
            Vehicle vehicle = vehicleOpt.get();
            RentDeal rentDeal = new RentDealFactory().createRentDeal(client, vehicle, startDate, endDate);
            dealDatabase.save(rentDeal);
            return new RentVehicleResponse(rentDeal);
        }

        return new RentVehicleResponse("Vehicle not found in database");
    }

    private Client getClient(GeneralRentVehicleRequest request) {
        return new Client(request.getPersonalId(), request.getFirstName(), request.getLastName(), request.getEmail(), request.getPhoneNumber());
    }
}
