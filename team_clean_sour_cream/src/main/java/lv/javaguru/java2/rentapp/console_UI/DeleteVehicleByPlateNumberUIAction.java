package lv.javaguru.java2.rentapp.console_UI;

import lv.javaguru.java2.rentapp.core.requests.DeleteVehicleByPlateNumberRequest;
import lv.javaguru.java2.rentapp.core.responses.DeleteVehicleByPlateNumberResponse;
import lv.javaguru.java2.rentapp.core.services.DeleteVehicleByPlateNumberService;

import java.util.Scanner;

public class DeleteVehicleByPlateNumberUIAction implements UIAction {

    private DeleteVehicleByPlateNumberService deleteVehicleByPlateNumberService;

    public DeleteVehicleByPlateNumberUIAction(DeleteVehicleByPlateNumberService deleteVehicleByPlateNumberService) {
        this.deleteVehicleByPlateNumberService = deleteVehicleByPlateNumberService;
    }

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
            checkIsVehicleDeleted(response);
        }
    }

    private String getFromUserNumberOfPlateToDelete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter vehicle plate number to delete.");
        return scanner.nextLine();
    }

    private void checkIsVehicleDeleted(DeleteVehicleByPlateNumberResponse response) {
        if (response.isVehicleDeleted()) {
            System.out.println("Your vehicle was removed from list.");
        } else {
            System.out.println("Vehicle was not found!");
        }
    }
}
