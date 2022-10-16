package lv.javaguru.java2.rentapp.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDate;

@Getter
@Setter
public class RentDeal {

    Long id;
    Client client;
    Vehicle vehicle;
    LocalDate startDate;
    Long rentDuration;
    LocalDate endDate;
    Double rentCost;

    public RentDeal(Client client, Vehicle vehicle, LocalDate startDate, LocalDate endDate) {
        this.client = client;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.rentDuration = Duration.between(startDate, endDate.plusDays(1)).toDays();
        this.rentCost = vehicle.getRentPricePerDay() * rentDuration;
        this.endDate = endDate;
    }
}
