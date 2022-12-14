package lv.javaguru.java2.repo_men_inc.core.services;

import lv.javaguru.java2.repo_men_inc.core.domain.Debtor;
import lv.javaguru.java2.repo_men_inc.core.requests.AddHarvestedItemRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.AddHarvestedItemResponse;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
import lv.javaguru.java2.repo_men_inc.core.validators.AddHarvestedItemValidator;
import lv.javaguru.java2.repo_men_inc.core.database.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Component
@Transactional
public class AddHarvestedItemService {
    @Autowired
    private Database database;
    @Autowired
    private AddHarvestedItemValidator addHarvestedItemValidator;

    public AddHarvestedItemResponse execute(AddHarvestedItemRequest addHarvestedItemRequest) {
        List<CoreError> errors = addHarvestedItemValidator.validate(addHarvestedItemRequest);
        if (!errors.isEmpty()) {
            return new AddHarvestedItemResponse(errors);
        }
//        boolean isHarvestedItemAdded = database.getById(addHarvestedItemRequest.getDebtorsId())
//                .addIem(addHarvestedItemRequest.getHarvestedItem());
//        return new AddHarvestedItemResponse(isHarvestedItemAdded);
        BigInteger newItemId = database.saveItem(addHarvestedItemRequest.getHarvestedItem());
        return new AddHarvestedItemResponse(database.updateList(newItemId, addHarvestedItemRequest.getDebtorsId()) == 1);
    }
}
