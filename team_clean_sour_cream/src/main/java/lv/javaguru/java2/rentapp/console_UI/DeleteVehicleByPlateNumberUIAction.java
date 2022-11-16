package lv.javaguru.java2.rentapp.console_UI;

import lv.javaguru.java2.rentapp.core.requests.DeleteVehicleByPlateNumberRequest;
import lv.javaguru.java2.rentapp.core.requests.ShowAllVehiclesPlateNumbersRequest;
import lv.javaguru.java2.rentapp.core.responses.DeleteVehicleByPlateNumberResponse;
import lv.javaguru.java2.rentapp.core.responses.ShowAllVehiclesPlateNumbersResponse;
import lv.javaguru.java2.rentapp.core.services.DeleteVehicleByPlateNumberService;
import lv.javaguru.java2.rentapp.core.services.ShowAllVehiclesPlateNumbersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteVehicleByPlateNumberUIAction implements UIAction {

    @Autowired
    private DeleteVehicleByPlateNumberService deleteVehicleByPlateNumberService;

    @Autowired
    private ShowAllVehiclesPlateNumbersService showAllVehiclesPlateNumbersService;

    @Override
    public void execute() {

        ShowAllVehiclesPlateNumbersRequest showPlateNumbersRequest = new ShowAllVehiclesPlateNumbersRequest();
        ShowAllVehiclesPlateNumbersResponse showPlateNumbersResponse = showAllVehiclesPlateNumbersService.execute(showPlateNumbersRequest);

        if (showPlateNumbersResponse.getMsg() != null) {
            System.out.println(showPlateNumbersResponse.getMsg());
        } else {
            System.out.println("Available plate number(-s):");
            showPlateNumbersResponse.getVehiclesPlateNumbers().forEach(System.out::println);
        }

        String plateNumberToDelete = getFromUserNumberOfPlateToDelete();

        DeleteVehicleByPlateNumberRequest deleteRequest = new DeleteVehicleByPlateNumberRequest(plateNumberToDelete);
        DeleteVehicleByPlateNumberResponse response = deleteVehicleByPlateNumberService.execute(deleteRequest);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
        } else {
            System.out.println(response.getVehicleDeletedMsg());
        }
    }

    private String getFromUserNumberOfPlateToDelete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter vehicle plate number to delete.");
        return scanner.nextLine();
    }
}
