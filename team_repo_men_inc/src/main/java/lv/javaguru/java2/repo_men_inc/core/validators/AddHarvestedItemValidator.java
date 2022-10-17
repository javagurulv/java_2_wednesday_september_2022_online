package lv.javaguru.java2.repo_men_inc.core.validators;

import lv.javaguru.java2.repo_men_inc.core.requests.AddHarvestedItemRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
import lv.javaguru.java2.repo_men_inc.database.Database;
import lv.javaguru.java2.repo_men_inc.dependency_injection.DIComponent;
import lv.javaguru.java2.repo_men_inc.dependency_injection.DIDependency;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DIComponent
public class AddHarvestedItemValidator {

    @DIDependency
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
        validateItemIsInTheList(addHarvestedItemRequest).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateItemIsEmpty(AddHarvestedItemRequest addHarvestedItemRequest) {
        return (addHarvestedItemRequest.getHarvestedItem() == null || addHarvestedItemRequest.getHarvestedItem().isEmpty())
                ? Optional.of(new CoreError("Item", "Cannot be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateItemIsInTheList(AddHarvestedItemRequest addHarvestedItemRequest) {
        return database.getById(addHarvestedItemRequest.getDebtorsId()).getList().contains(addHarvestedItemRequest.getHarvestedItem())
                ? Optional.of(new CoreError("Item", "Already in the List!"))
                : Optional.empty();
    }
}
