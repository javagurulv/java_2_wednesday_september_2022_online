package lv.javaguru.java2.repo_men_inc.services;

import lv.javaguru.java2.repo_men_inc.core.requests.AddHarvestedItemRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.AddHarvestedItemResponse;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
import lv.javaguru.java2.repo_men_inc.core.validators.AddHarvestedItemValidator;
import lv.javaguru.java2.repo_men_inc.database.Database;

import java.util.List;

public class AddHarvestedItemService {
    private Database database;
    private AddHarvestedItemValidator addHarvestedItemValidator;

    public AddHarvestedItemService(Database database, AddHarvestedItemValidator addHarvestedItemValidator) {
        this.database = database;
        this.addHarvestedItemValidator = addHarvestedItemValidator;
    }

    public AddHarvestedItemResponse execute(AddHarvestedItemRequest addHarvestedItemRequest) {
        List<CoreError> errors = addHarvestedItemValidator.validate(addHarvestedItemRequest);
        if (!errors.isEmpty()) {
            return new AddHarvestedItemResponse(errors);
        }
        boolean isHarvestedItemAdded = database.getById(addHarvestedItemRequest.getDebtorsId())
                .addIem(addHarvestedItemRequest.getHarvestedItem());
        return new AddHarvestedItemResponse(isHarvestedItemAdded);
    }
}
