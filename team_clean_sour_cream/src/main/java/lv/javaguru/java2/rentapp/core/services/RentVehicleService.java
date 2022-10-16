package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.DealDatabase;
import lv.javaguru.java2.rentapp.core.database.VehicleDatabase;
import lv.javaguru.java2.rentapp.core.requests.RentVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.RentVehicleResponse;
import lv.javaguru.java2.rentapp.domain.Client;
import lv.javaguru.java2.rentapp.domain.RentDeal;
import lv.javaguru.java2.rentapp.domain.Vehicle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RentVehicleService {

    private DealDatabase dealDatabase;
    private VehicleDatabase vehicleDatabase;

    public RentVehicleService(DealDatabase dealDatabase, VehicleDatabase vehicleDatabase) {
        this.dealDatabase = dealDatabase;
        this.vehicleDatabase = vehicleDatabase;
    }

    public RentVehicleResponse execute(RentVehicleRequest request) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(request.getStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(request.getEndDate(), formatter);
        Client client = new Client(request.getPersonalId(), request.getFirstName(), request.getLastName(), request.getEmail(), request.getPhoneNumber());
        Vehicle vehicle = vehicleDatabase.getById(request.getVehicleId());

        RentDeal rentDeal = new RentDeal(client, vehicle, startDate, endDate);
        dealDatabase.save(rentDeal);

        return new RentVehicleResponse();
    }
}
