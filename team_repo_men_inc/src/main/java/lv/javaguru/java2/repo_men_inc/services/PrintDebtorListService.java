package lv.javaguru.java2.repo_men_inc.services;

import lv.javaguru.java2.repo_men_inc.domain.Debtor;
import lv.javaguru.java2.repo_men_inc.core.requests.PrintDebtorsListRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.PrintDebtorsListResponse;
import lv.javaguru.java2.repo_men_inc.database.Database;

import java.util.List;

public class PrintDebtorListService {
    Database database;

    public PrintDebtorListService(Database database) {
        this.database = database;
    }

    public PrintDebtorsListResponse execute(PrintDebtorsListRequest printDebtorsListRequest) {
        List<Debtor> debtors = database.getAllDebtors();
        return new PrintDebtorsListResponse(debtors);
    }
}