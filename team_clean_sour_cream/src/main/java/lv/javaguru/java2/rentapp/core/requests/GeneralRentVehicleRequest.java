package lv.javaguru.java2.rentapp.core.requests;

import lombok.Builder;
import lombok.Getter;
import lv.javaguru.java2.rentapp.domain.Vehicle;

import java.util.List;

@Getter
@Builder
public class GeneralRentVehicleRequest {

    private Long vehicleId;

    private String personalId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    private String rentStartDate;
    private String rentEndDate;

    private Paging paging;

    private SearchVehicleRequest searchVehicleRequest;

    private List<Vehicle> availableVehicles;

}
