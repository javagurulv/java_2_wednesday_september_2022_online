package apartment.console_ui;

import apartment.core.request.RemoveApartmentRequest;
import apartment.core.response.RemoveApartmentResponse;
import apartment.core.services.RemoveApartmentService;

import java.util.Scanner;

public class RemoveApartmentUIUIAction implements UIAction {

    private RemoveApartmentService removeApartmentService;
    public RemoveApartmentUIUIAction(RemoveApartmentService removeApartmentService) {

        this.removeApartmentService = removeApartmentService;
    }

    @Override
    public void execute(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter apartment's ID to remove ");
        Long apartmentId = Long.parseLong(scanner.nextLine());
        RemoveApartmentRequest request = new RemoveApartmentRequest(apartmentId);
        RemoveApartmentResponse response = removeApartmentService.execute(request);
        if (response.isApartmentRemoved()){
            System.out.println("Your apartment was removed from List");
        } else {
            System.out.println("Your book isn't removed from List");
        }

    }

    /*public void execute(List<Apartment> apartmentList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter apartment's adress ");
        String apartmentAddress = scanner.nextLine();
        System.out.println("Enter apartment's rooms amount ");
        String apartmentRoomsAmount = scanner.nextLine();
        System.out.println("Enter apartment's floor ");
        String apartmentFloor = scanner.nextLine();
        System.out.println("Enter apartment's area ");
        String apartmentArea = scanner.nextLine();
        database.deleteById(apartmentId);
        System.out.println("Your apartment was removed from list.");

    }*/
}
