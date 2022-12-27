package lv.javaguru.java2.repo_men_inc.core.validators;

import lv.javaguru.java2.repo_men_inc.core.domain.Item;
import lv.javaguru.java2.repo_men_inc.core.requests.AddHarvestedItemRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
import lv.javaguru.java2.repo_men_inc.core.database.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AddHarvestedItemValidator {

    @Autowired
    private Database database;

    public AddHarvestedItemValidator() {
    }

    public AddHarvestedItemValidator(Database database) {
        this.database = database;
    }

    public List<CoreError> validate(AddHarvestedItemRequest addHarvestedItemRequest) {
        List<CoreError> errors = new ArrayList<>();
        if (database.getById(addHarvestedItemRequest.getDebtorsId()) == null) {
            errors.add(new CoreError("Debtor ID", "Debtor not found!"));
            return errors;
        }
        validateItemIsEmpty(addHarvestedItemRequest).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateItemIsEmpty(AddHarvestedItemRequest addHarvestedItemRequest) {
        return (addHarvestedItemRequest.getHarvestedItem() == null || addHarvestedItemRequest.getHarvestedItem().isEmpty())
                ? Optional.of(new CoreError("Item", "Cannot be empty!"))
                : Optional.empty();
    }
}
