package lv.javaguru.java2.repo_men_inc.core.responses;

import lv.javaguru.java2.repo_men_inc.core.domain.Debtor;

import java.util.List;

public class PrintDebtorsListResponse {
    private List<Debtor> debtors;

    public PrintDebtorsListResponse(List<Debtor> debtors) {
        this.debtors = debtors;
    }

    public List<Debtor> getDebtors() {
        return debtors;
    }
}
