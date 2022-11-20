package lv.javaguru.java2.rentapp.core.responses;

import lombok.Getter;

import java.util.List;

@Getter
public class ShowAllVehiclesPlateNumbersResponse extends CoreResponse {

    private List<String> vehiclesPlateNumbers;

    private String msg;

    public ShowAllVehiclesPlateNumbersResponse(List<String> vehiclesPlateNumbers) {
        this.vehiclesPlateNumbers = vehiclesPlateNumbers;
    }

    public ShowAllVehiclesPlateNumbersResponse(String msg) {
        this.msg = msg;
    }
}
