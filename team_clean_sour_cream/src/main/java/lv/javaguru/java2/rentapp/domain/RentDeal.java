package lv.javaguru.java2.rentapp.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Duration;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
@ToString
public class RentDeal {

    private Long id;
    private Client client;
    private Vehicle vehicle;
    private LocalDate startDate;
    private Long rentDuration;
    private LocalDate endDate;
    private Double rentCost;

    public RentDeal(Client client, Vehicle vehicle, LocalDate startDate, LocalDate endDate) {
        this.client = client;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.rentDuration = TimeUnit.DAYS.toDays(Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays());
        this.rentCost = vehicle.getRentPricePerDay() * rentDuration;
        this.endDate = endDate;
    }
}
