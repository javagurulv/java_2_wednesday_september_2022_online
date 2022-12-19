package lv.javaguru.java2.repo_men_inc.core.services;

import lv.javaguru.java2.repo_men_inc.core.requests.PrintDebtorsListRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.PrintDebtorsListResponse;
import lv.javaguru.java2.repo_men_inc.core.database.Database;
import lv.javaguru.java2.repo_men_inc.core.domain.Debtor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrintDebtorListService {
    @Autowired
    Database database;

    public PrintDebtorsListResponse execute(PrintDebtorsListRequest printDebtorsListRequest) {
        List<Debtor> debtors = database.getAllDebtors();
        return new PrintDebtorsListResponse(debtors);
    }
}
