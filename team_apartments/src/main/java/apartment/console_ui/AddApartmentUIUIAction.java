package apartment.console_ui;

import apartment.core.request.AddApartmentRequest;
import apartment.core.response.AddApartmentResponse;
import apartment.core.services.AddApartmentService;

import java.util.Scanner;

public class AddApartmentUIUIAction implements UIAction {

    private AddApartmentService addApartmentService;

    public AddApartmentUIUIAction(AddApartmentService addApartmentService) {
        this.addApartmentService = addApartmentService;
    }

    @Override
    public void execute (){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter apartment address: ");
        String apartmentAddress = scanner.nextLine();
        System.out.println("Enter apartment's rooms amount: ");
        Long apartmentRoomsAmount = scanner.nextLong();
        System.out.println("Enter apartment's floor: ");
        Long apartmentFloor = scanner.nextLong();
        System.out.println("Enter apartment's area: ");
        Long apartmentArea = scanner.nextLong();
        AddApartmentRequest request = new AddApartmentRequest(apartmentAddress, apartmentRoomsAmount, apartmentFloor, apartmentArea);
        AddApartmentResponse response = addApartmentService.execute(request);

        if (response.hasErrors()){
            response.getErrors().forEach(coreError ->
                            System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
                    );
        } else {
            System.out.println("New book id was: " + response.getNewApartment().getId());
            System.out.println("Your book was added to list.");
        }
    }

}
