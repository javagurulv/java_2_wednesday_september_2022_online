package lv.javaguru.java2.repo_men_inc.services;

import lv.javaguru.java2.repo_men_inc.core.requests.AddHarvestedItemRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.AddHarvestedItemResponse;
import lv.javaguru.java2.repo_men_inc.database.Database;

public class AddHarvestedItemService {
    Database database;

    public AddHarvestedItemService(Database database) {
        this.database = database;
    }

    public AddHarvestedItemResponse execute(AddHarvestedItemRequest addHarvestedItemRequest) {
        boolean isHarvestedItemAdded = database.getById(addHarvestedItemRequest.getDebtorsId())
                .addIem(addHarvestedItemRequest.getHarvestedItem());
        return new AddHarvestedItemResponse(isHarvestedItemAdded);
    }
}
