package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.DealDatabase;
import lv.javaguru.java2.rentapp.core.database.VehicleDatabase;
import lv.javaguru.java2.rentapp.core.requests.FindAvailableVehiclesRequest;
import lv.javaguru.java2.rentapp.core.responses.FindAvailableVehiclesResponse;
import lv.javaguru.java2.rentapp.domain.RentDeal;
import lv.javaguru.java2.rentapp.domain.Vehicle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FindAvailableVehiclesService {

    private DealDatabase dealDatabase;
    private VehicleDatabase vehicleDatabase;

    public FindAvailableVehiclesService(DealDatabase dealDatabase, VehicleDatabase vehicleDatabase) {
        this.dealDatabase = dealDatabase;
        this.vehicleDatabase = vehicleDatabase;
    }

    public FindAvailableVehiclesResponse execute(FindAvailableVehiclesRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(request.getStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(request.getEndDate(), formatter);
        List<RentDeal> rentDeals = dealDatabase.getAllDeals();
        List<Vehicle> unavailableVehicles = new ArrayList<>();

        for (RentDeal rentDeal : rentDeals) {
            if ((startDate.isAfter(rentDeal.getStartDate()) && startDate.isBefore(rentDeal.getEndDate()))
                    || (endDate.isAfter(rentDeal.getStartDate()) && endDate.isBefore(rentDeal.getEndDate()))) {
                unavailableVehicles.add(rentDeal.getVehicle());
            }
        }
        List<Vehicle> availableVehicles = vehicleDatabase.getAllVehicles();
        availableVehicles.removeAll(unavailableVehicles);
        return new FindAvailableVehiclesResponse(null, availableVehicles);
    }

}
