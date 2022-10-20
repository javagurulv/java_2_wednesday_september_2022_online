package lv.javaguru.java2.repo_men_inc.services;

import lv.javaguru.java2.repo_men_inc.core.requests.AddHarvestedItemRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.AddHarvestedItemResponse;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
import lv.javaguru.java2.repo_men_inc.core.validators.AddHarvestedItemValidator;
import lv.javaguru.java2.repo_men_inc.database.Database;
import lv.javaguru.java2.repo_men_inc.dependency_injection.DIComponent;
import lv.javaguru.java2.repo_men_inc.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class AddHarvestedItemService {
    @DIDependency
    private Database database;
    @DIDependency
    private AddHarvestedItemValidator addHarvestedItemValidator;

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
