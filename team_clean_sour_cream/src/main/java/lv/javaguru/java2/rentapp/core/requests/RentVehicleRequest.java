package lv.javaguru.java2.rentapp.core.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RentVehicleRequest {

    private Long vehicleId;

    private String personalId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    private String rentStartDate;
    private String rentEndDate;

}
