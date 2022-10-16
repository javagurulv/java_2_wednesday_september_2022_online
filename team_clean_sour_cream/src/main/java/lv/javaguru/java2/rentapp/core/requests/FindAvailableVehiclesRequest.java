package lv.javaguru.java2.rentapp.core.requests;

import lombok.Getter;

@Getter
public class FindAvailableVehiclesRequest {

    String startDate;
    String endDate;

}
