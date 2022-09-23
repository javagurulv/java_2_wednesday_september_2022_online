package lv.javaguru.java2.repo_men_inc.services;

import lv.javaguru.java2.repo_men_inc.core.requests.RemoveDebtorRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.RemoveDebtorResponse;
import lv.javaguru.java2.repo_men_inc.database.Database;

public class RemoveDebtorService {
    Database database;

    public RemoveDebtorService(Database database) {
        this.database = database;
    }

    public RemoveDebtorResponse execute(RemoveDebtorRequest removeDebtorRequest) {
        boolean isDebtorRemoved = database.deleteById(removeDebtorRequest.getDebtorsId());
        return new RemoveDebtorResponse(isDebtorRemoved);
    }
}
