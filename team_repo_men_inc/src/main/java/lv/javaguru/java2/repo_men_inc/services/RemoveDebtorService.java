package lv.javaguru.java2.repo_men_inc.services;

import lv.javaguru.java2.repo_men_inc.core.requests.RemoveDebtorRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
import lv.javaguru.java2.repo_men_inc.core.responses.RemoveDebtorResponse;
import lv.javaguru.java2.repo_men_inc.core.validators.RemoveDebtorValidator;
import lv.javaguru.java2.repo_men_inc.database.Database;

import java.util.List;

public class RemoveDebtorService {
    private Database database;
    private RemoveDebtorValidator removeDebtorValidator;

    public RemoveDebtorService(Database database, RemoveDebtorValidator removeDebtorValidator) {
        this.database = database;
        this.removeDebtorValidator = removeDebtorValidator;
    }

    public RemoveDebtorResponse execute(RemoveDebtorRequest removeDebtorRequest) {

        List<CoreError> errors = removeDebtorValidator.validate(removeDebtorRequest);
        if (!errors.isEmpty()) {
            return new RemoveDebtorResponse(errors);
        }

        boolean isDebtorRemoved = database.deleteById(removeDebtorRequest.getDebtorsId());
        return new RemoveDebtorResponse(isDebtorRemoved);
    }
}
