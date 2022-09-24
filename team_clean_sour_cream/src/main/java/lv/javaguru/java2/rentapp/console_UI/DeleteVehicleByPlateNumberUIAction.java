package lv.javaguru.java2.rentapp.console_UI;

import lv.javaguru.java2.rentapp.core.requests.DeleteVehicleByPlateNumberRequest;
import lv.javaguru.java2.rentapp.core.responses.DeleteVehicleByPlateNumberResponse;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import lv.javaguru.java2.rentapp.core.services.DeleteVehicleByPlateNumberService;
import java.util.Scanner;

public class DeleteVehicleByPlateNumberUIAction implements UIAction {

    private DeleteVehicleByPlateNumberService deleteVehicleByPlateNumberService;

    public DeleteVehicleByPlateNumberUIAction(DeleteVehicleByPlateNumberService deleteVehicleByPlateNumberService) {
        this.deleteVehicleByPlateNumberService = deleteVehicleByPlateNumberService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Available are:");
        showAllVehiclesPlateNumbers();

        System.out.println("Enter vehicle plate number to delete.");
        String plateNumberToDelete = scanner.nextLine();
        DeleteVehicleByPlateNumberRequest request = new DeleteVehicleByPlateNumberRequest(plateNumberToDelete);

        DeleteVehicleByPlateNumberResponse response = deleteVehicleByPlateNumberService.execute(request);

        if (response.isVehicleDeleted()) {
            System.out.println("Your vehicle was removed from list.");
        } else {
            System.out.println("Vehicle was not found!");
        }
    }

    private void showAllVehiclesPlateNumbers() {
        deleteVehicleByPlateNumberService.getDatabase().getAllVehicles().stream()
                .map(Vehicle::getPlateNumber)
                .forEach(System.out::println);
    }
}
