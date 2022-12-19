package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.DealDatabase;
import lv.javaguru.java2.rentapp.core.requests.GeneralRentVehicleRequest;
import lv.javaguru.java2.rentapp.core.requests.Paging;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.responses.VehicleAvailabilityResponse;
import lv.javaguru.java2.rentapp.core.services.validators.VehicleAvailabilityServiceValidator;
import lv.javaguru.java2.rentapp.domain.RentDeal;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VehicleAvailabilityService {

    @Autowired
    private DealDatabase dealDatabase;

    @Autowired
    private VehicleAvailabilityServiceValidator vehicleAvailabilityServiceValidator;

    public VehicleAvailabilityResponse execute(GeneralRentVehicleRequest request, List<Vehicle> vehicles) {
        List<CoreError> errors = vehicleAvailabilityServiceValidator.validate(request);
        if (!errors.isEmpty()) {
            return new VehicleAvailabilityResponse(errors, null);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate startDate = LocalDate.parse(request.getRentStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(request.getRentEndDate(), formatter);

        List<Vehicle> availableVehicles = findAvailableVehiclesInRange(startDate, endDate, vehicles);
        VehicleAvailabilityResponse response = new VehicleAvailabilityResponse(null, availableVehicles);
        response.setVehiclesPaged(paging(availableVehicles, request.getPaging()));

        return response;
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

    private List<Vehicle> findAvailableVehiclesInRange(LocalDate startDate, LocalDate endDate, List<Vehicle> vehicles) {
        List<RentDeal> rentDeals = dealDatabase.getAllDeals();

        List<Vehicle> unavailableVehicles = rentDeals.stream()
                .filter(rentDeal -> isNotAvailableInGivenRange(startDate, endDate, rentDeal))
                .map(RentDeal::getVehicle).toList();

        vehicles.removeAll(unavailableVehicles);
        return vehicles;
    }

    private boolean isNotAvailableInGivenRange(LocalDate startDate, LocalDate endDate, RentDeal rentDeal) {
        return checkStartDate(startDate, rentDeal) || checkEndDate(endDate, rentDeal);
    }

    private boolean checkStartDate(LocalDate newDealStartDate, RentDeal rentDeal) {
        LocalDate existingDealStartDate = rentDeal.getStartDate();
        LocalDate existingDealEndDate = rentDeal.getEndDate();
        return ((newDealStartDate.isAfter(existingDealStartDate) || newDealStartDate.isEqual(existingDealStartDate))
                && (newDealStartDate.isBefore(existingDealEndDate) || newDealStartDate.isEqual(existingDealEndDate)));
    }

    private boolean checkEndDate(LocalDate newDealEndDate, RentDeal rentDeal) {
        LocalDate existingDealStartDate = rentDeal.getStartDate();
        LocalDate existingDealEndDate = rentDeal.getEndDate();
        return ((newDealEndDate.isAfter(existingDealStartDate) || newDealEndDate.isEqual(existingDealStartDate))
                && (newDealEndDate.isBefore(existingDealEndDate) || newDealEndDate.isEqual(existingDealEndDate)));
    }
}
