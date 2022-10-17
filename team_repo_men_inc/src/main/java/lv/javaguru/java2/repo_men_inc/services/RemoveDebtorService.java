package lv.javaguru.java2.repo_men_inc.services;

import lv.javaguru.java2.repo_men_inc.core.requests.RemoveDebtorRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
import lv.javaguru.java2.repo_men_inc.core.responses.RemoveDebtorResponse;
import lv.javaguru.java2.repo_men_inc.core.validators.RemoveDebtorValidator;
import lv.javaguru.java2.repo_men_inc.database.Database;
import lv.javaguru.java2.repo_men_inc.dependency_injection.DIComponent;
import lv.javaguru.java2.repo_men_inc.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class RemoveDebtorService {
    @DIDependency
    private Database database;
    @DIDependency
    private RemoveDebtorValidator removeDebtorValidator;

    public RemoveDebtorResponse execute(RemoveDebtorRequest removeDebtorRequest) {

        List<CoreError> errors = removeDebtorValidator.validate(removeDebtorRequest);
        if (!errors.isEmpty()) {
            return new RemoveDebtorResponse(errors);
        }

        boolean isDebtorRemoved = database.deleteById(removeDebtorRequest.getDebtorsId());
        return new RemoveDebtorResponse(isDebtorRemoved);
    }
}
