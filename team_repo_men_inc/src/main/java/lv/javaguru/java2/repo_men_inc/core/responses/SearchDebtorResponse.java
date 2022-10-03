package lv.javaguru.java2.repo_men_inc.core.responses;

import lv.javaguru.java2.repo_men_inc.domain.Debtor;

import java.util.List;

public class SearchDebtorResponse extends CoreResponse{

    private List<Debtor> debtors;

    public SearchDebtorResponse(List<CoreError> errors, boolean erroneous) {
        super(errors);
    }

    public SearchDebtorResponse(List<Debtor> debtors) {
        this.debtors = debtors;
    }

    public List<Debtor> getDebtors() {
        return debtors;
    }
}
