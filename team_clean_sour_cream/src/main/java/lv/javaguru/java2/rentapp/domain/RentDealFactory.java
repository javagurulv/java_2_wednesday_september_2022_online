package lv.javaguru.java2.rentapp.domain;

import java.time.Duration;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

@Component
public class RentDealFactory {

    public RentDeal createRentDeal(Client client, Vehicle vehicle, LocalDate startDate, LocalDate endDate) {
        RentDeal rentDeal = new RentDeal();
        rentDeal.setClient(client);
        rentDeal.setVehicle(vehicle);
        rentDeal.setStartDate(startDate);
        rentDeal.setRentDuration(calculateRentDuration(startDate, endDate));
        rentDeal.setRentCost(calculateRentCost(vehicle, rentDeal.getRentDuration()));
        rentDeal.setEndDate(endDate);
        return rentDeal;
    }

    private Double calculateRentCost(Vehicle vehicle, Long rentDuration) {
        return vehicle.getRentPricePerDay() * rentDuration;
    }

    private long calculateRentDuration(LocalDate startDate, LocalDate endDate) {
        return TimeUnit.DAYS.toDays(Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays());
    }

}
