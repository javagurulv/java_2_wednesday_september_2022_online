package lv.javaguru.java2.rentapp.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RentDeal {

    private Long id;
    private Client client;
    private Vehicle vehicle;
    private LocalDate startDate;
    private Long rentDuration;
    private LocalDate endDate;
    private Double rentCost;

    public RentDeal() {
    }
}
