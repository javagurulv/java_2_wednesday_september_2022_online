package lv.javaguru.java2.repo_men_inc.console_ui;

import lv.javaguru.java2.repo_men_inc.core.requests.AddHarvestedItemRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.AddHarvestedItemResponse;
import lv.javaguru.java2.repo_men_inc.services.AddHarvestedItemService;

import java.util.Scanner;

public class AddHarvestedItemUIAction implements UIAction{
    AddHarvestedItemService addHarvestedItemService;
    Scanner scanner;

    public AddHarvestedItemUIAction(AddHarvestedItemService addHarvestedItemService, Scanner scanner) {
        this.addHarvestedItemService = addHarvestedItemService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("Enter debtors Id: ");
        Long debtorsId = Long.parseLong(scanner.nextLine());
        System.out.println("Enter harvested item");
        String harvestedItem = scanner.nextLine();
        AddHarvestedItemRequest addHarvestedItemRequest = new AddHarvestedItemRequest(debtorsId, harvestedItem);
        AddHarvestedItemResponse addHarvestedItemResponse = addHarvestedItemService.execute(addHarvestedItemRequest);
        if (addHarvestedItemResponse.isHarvestedItemAdded()) {
            System.out.println("Harvested Item added to debtors list.");
        } else {
            System.out.println("Failed to add Harvested Item to debtors list.");
        }
    }
}
