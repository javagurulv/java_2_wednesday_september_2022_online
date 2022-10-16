package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.DealDatabase;
import lv.javaguru.java2.rentapp.core.database.VehicleDatabase;
import lv.javaguru.java2.rentapp.core.requests.GeneralRentVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.VehicleAvailabilityResponse;
import lv.javaguru.java2.rentapp.domain.RentDeal;
import lv.javaguru.java2.rentapp.domain.Vehicle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VehicleAvailabilityService {

    private DealDatabase dealDatabase;
    private VehicleDatabase vehicleDatabase;

    public VehicleAvailabilityService(DealDatabase dealDatabase, VehicleDatabase vehicleDatabase) {
        this.dealDatabase = dealDatabase;
        this.vehicleDatabase = vehicleDatabase;
    }

    public VehicleAvailabilityResponse execute(GeneralRentVehicleRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate startDate = LocalDate.parse(request.getRentStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(request.getRentEndDate(), formatter);

        List<Vehicle> availableVehicles = findAvailableVehiclesInRange(startDate, endDate);

        return new VehicleAvailabilityResponse(null, availableVehicles);
    }

    private List<Vehicle> findAvailableVehiclesInRange(LocalDate startDate, LocalDate endDate) {
        List<RentDeal> rentDeals = dealDatabase.getAllDeals();

        List<Vehicle> unavailableVehicles = rentDeals.stream()
                .filter(rentDeal -> isNotAvailableInGivenRange(startDate, endDate, rentDeal))
                .map(RentDeal::getVehicle).toList();

        List<Vehicle> availableVehicles = vehicleDatabase.getAllVehicles();
        availableVehicles.removeAll(unavailableVehicles);
        return availableVehicles;
    }

    private boolean isNotAvailableInGivenRange(LocalDate startDate, LocalDate endDate, RentDeal rentDeal) {
        return (startDate.isAfter(rentDeal.getStartDate()) && startDate.isBefore(rentDeal.getEndDate()))
                || (endDate.isAfter(rentDeal.getStartDate()) && endDate.isBefore(rentDeal.getEndDate()));
    }
}
