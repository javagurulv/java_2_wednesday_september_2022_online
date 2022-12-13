package lv.javaguru.java2.repo_men_inc.core.validators;

import lv.javaguru.java2.repo_men_inc.core.requests.RemoveDebtorRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
import lv.javaguru.java2.repo_men_inc.core.database.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RemoveDebtorValidator {

    @Autowired
    private Database database;

    public RemoveDebtorValidator() {
    }

    public RemoveDebtorValidator(Database database) {
        this.database = database;
    }

    public List<CoreError> validate(RemoveDebtorRequest removeDebtorRequest) {
        List<CoreError> errors = new ArrayList<>();
        validateId(removeDebtorRequest).ifPresent(errors::add);
        checkThatDebtorExists(removeDebtorRequest).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(RemoveDebtorRequest removeDebtorRequest) {
        return (removeDebtorRequest.getDebtorsId() == null || removeDebtorRequest.getDebtorsId().describeConstable().isEmpty())
                ? Optional.of(new CoreError("ID", "Cannot be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> checkThatDebtorExists(RemoveDebtorRequest removeDebtorRequest) {
        return database.getById(removeDebtorRequest.getDebtorsId()) == null
                ? Optional.of(new CoreError("ID", "Debtor not found!"))
                : Optional.empty();
    }

}
