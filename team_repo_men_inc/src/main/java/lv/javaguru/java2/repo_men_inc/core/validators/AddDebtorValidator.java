package lv.javaguru.java2.repo_men_inc.core.validators;

import lv.javaguru.java2.repo_men_inc.core.requests.AddDebtorRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
import lv.javaguru.java2.repo_men_inc.database.Database;
import lv.javaguru.java2.repo_men_inc.dependency_injection.DIComponent;
import lv.javaguru.java2.repo_men_inc.dependency_injection.DIDependency;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DIComponent
public class AddDebtorValidator {

    @DIDependency
    private Database database;

    public AddDebtorValidator() {
    }

    public AddDebtorValidator(Database database) {
        this.database = database;
    }

    public List<CoreError> validate(AddDebtorRequest addDebtorRequest) {
        List<CoreError> errors = new ArrayList<>();
        validateName(addDebtorRequest).ifPresent(errors::add);
        checkDuplicate(addDebtorRequest).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateName(AddDebtorRequest addDebtorRequest) {
        return (addDebtorRequest.getName() == null || addDebtorRequest.getName().isEmpty())
                ? Optional.of(new CoreError("Name", "Cannot be empty!"))
                : Optional.empty();
    }

    public Optional<CoreError> checkDuplicate(AddDebtorRequest addDebtorRequest) {
        return database.getByName(addDebtorRequest.getName()) != null
                ? Optional.of(new CoreError("Name", "Already exist"))
                : Optional.empty();
    }
}
