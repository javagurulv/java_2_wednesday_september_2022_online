package lv.javaguru.java2.repo_men_inc.console_ui;

import lv.javaguru.java2.repo_men_inc.core.requests.AddHarvestedItemRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.AddHarvestedItemResponse;
import lv.javaguru.java2.repo_men_inc.services.AddHarvestedItemService;

import java.util.Scanner;

public class AddHarvestedItemUIAction implements UIAction{
    private AddHarvestedItemService addHarvestedItemService;

    public AddHarvestedItemUIAction(AddHarvestedItemService addHarvestedItemService) {
        this.addHarvestedItemService = addHarvestedItemService;
    }

    @Override
    public void execute() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter debtors Id: ");
            Long debtorsId = Long.parseLong(scanner.nextLine());
            System.out.println("Enter harvested item");
            String harvestedItem = scanner.nextLine();
            AddHarvestedItemRequest addHarvestedItemRequest = new AddHarvestedItemRequest(debtorsId, harvestedItem);
            AddHarvestedItemResponse addHarvestedItemResponse = addHarvestedItemService.execute(addHarvestedItemRequest);

            if (addHarvestedItemResponse.hasErrors()) {
                System.out.println("===================== errors =====================");
                addHarvestedItemResponse.getErrors().forEach(coreError -> System.out.println(coreError.getField() + ": " + coreError.getMessage()));
                System.out.println("==================================================");
            } else {
                if (addHarvestedItemResponse.isHarvestedItemAdded()) {
                    System.out.println("Harvested Item added to debtors list.");
                } else {
                    System.out.println("Failed to add Harvested Item to debtors list.");
                }
            }
        } catch (NumberFormatException numberFormatException) {
            System.out.println("INVALID INPUT!");
        }
    }
}
