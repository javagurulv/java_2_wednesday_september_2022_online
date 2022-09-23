package lv.javaguru.java2.repo_men_inc.services;

import lv.javaguru.java2.repo_men_inc.Debtor;
import lv.javaguru.java2.repo_men_inc.core.requests.AddDebtorRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.AddDebtorResponse;
import lv.javaguru.java2.repo_men_inc.database.Database;

public class AddDebtorService {
    Database database;

    public AddDebtorService(Database database) {
        this.database = database;
    }

    public AddDebtorResponse execute(AddDebtorRequest addDebtorRequest) {
        Debtor debtor = new Debtor(addDebtorRequest.getName());
        boolean isDebtorSavedToDatabase = database.save(debtor);
        return new AddDebtorResponse(isDebtorSavedToDatabase);
    }
}
