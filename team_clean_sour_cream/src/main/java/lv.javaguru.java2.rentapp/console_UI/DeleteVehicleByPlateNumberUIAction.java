package lv.javaguru.java2.rentapp.console_UI;

import lv.javaguru.java2.rentapp.domain.Vehicle;
import lv.javaguru.java2.rentapp.services.DeleteVehicleByPlateNumberService;
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

        deleteVehicleByPlateNumberService.execute(plateNumberToDelete);
        System.out.println("Your vehicle was removed from list.");
    }

    private void showAllVehiclesPlateNumbers() {
        deleteVehicleByPlateNumberService.getDatabase().getAllVehicles().stream()
                .map(Vehicle::getPlateNumber)
                .forEach(System.out::println);
    }
}
