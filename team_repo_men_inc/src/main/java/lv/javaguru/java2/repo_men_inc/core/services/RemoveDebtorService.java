package lv.javaguru.java2.repo_men_inc.core.services;

import lv.javaguru.java2.repo_men_inc.core.requests.RemoveDebtorRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
import lv.javaguru.java2.repo_men_inc.core.responses.RemoveDebtorResponse;
import lv.javaguru.java2.repo_men_inc.core.validators.RemoveDebtorValidator;
import lv.javaguru.java2.repo_men_inc.core.database.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RemoveDebtorService {
    @Autowired
    private Database database;
    @Autowired
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
