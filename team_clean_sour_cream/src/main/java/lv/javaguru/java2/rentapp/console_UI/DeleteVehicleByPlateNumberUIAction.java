package lv.javaguru.java2.rentapp.console_UI;

import lv.javaguru.java2.rentapp.core.requests.DeleteVehicleByPlateNumberRequest;
import lv.javaguru.java2.rentapp.core.responses.DeleteVehicleByPlateNumberResponse;
import lv.javaguru.java2.rentapp.core.services.DeleteVehicleByPlateNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteVehicleByPlateNumberUIAction implements UIAction {

    @Autowired
    private DeleteVehicleByPlateNumberService deleteVehicleByPlateNumberService;

    @Override
    public void execute() {

        deleteVehicleByPlateNumberService.showAllVehiclesPlateNumbers();

        String plateNumberToDelete = getFromUserNumberOfPlateToDelete();

        DeleteVehicleByPlateNumberRequest request = new DeleteVehicleByPlateNumberRequest(plateNumberToDelete);
        DeleteVehicleByPlateNumberResponse response = deleteVehicleByPlateNumberService.execute(request);

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
