package lv.javaguru.java2.repo_men_inc.services;

import lv.javaguru.java2.repo_men_inc.domain.Debtor;
import lv.javaguru.java2.repo_men_inc.core.requests.AddDebtorRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.AddDebtorResponse;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
import lv.javaguru.java2.repo_men_inc.core.validators.AddDebtorValidator;
import lv.javaguru.java2.repo_men_inc.database.Database;

import java.util.List;

public class AddDebtorService {
    private Database database;
    private AddDebtorValidator addDebtorValidator;

    public AddDebtorService(Database database, AddDebtorValidator addDebtorValidator) {
        this.database = database;
        this.addDebtorValidator = addDebtorValidator;
    }

    public AddDebtorResponse execute(AddDebtorRequest addDebtorRequest) {

        List<CoreError> errors = addDebtorValidator.validate(addDebtorRequest);
        if (!errors.isEmpty()) {
            return new AddDebtorResponse(errors);
        }

        Debtor debtor = new Debtor(addDebtorRequest.getName());
        boolean isDebtorSavedToDatabase = database.save(debtor);
        return new AddDebtorResponse(isDebtorSavedToDatabase);
    }
}
