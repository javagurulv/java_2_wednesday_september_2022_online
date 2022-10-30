package lv.javaguru.java2.repo_men_inc.core.services;

import lv.javaguru.java2.repo_men_inc.core.requests.AddDebtorRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.AddDebtorResponse;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
import lv.javaguru.java2.repo_men_inc.core.validators.AddDebtorValidator;
import lv.javaguru.java2.repo_men_inc.core.database.Database;
import lv.javaguru.java2.repo_men_inc.core.domain.Debtor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddDebtorService {
    @Autowired
    private Database database;
    @Autowired
    private AddDebtorValidator addDebtorValidator;

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
