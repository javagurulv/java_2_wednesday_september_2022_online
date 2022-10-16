package lv.javaguru.java2.rentapp.core.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VehicleAvailabilityRequest {

    String rentStartDate;
    String rentEndDate;

}
