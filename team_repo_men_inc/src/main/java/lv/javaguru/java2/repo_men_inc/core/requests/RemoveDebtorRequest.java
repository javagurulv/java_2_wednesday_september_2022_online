package lv.javaguru.java2.repo_men_inc.core.requests;

public class RemoveDebtorRequest {

    private Long debtorsId;

    public RemoveDebtorRequest(Long debtorsId) {
        this.debtorsId = debtorsId;
    }

    public Long getDebtorsId() {
        return debtorsId;
    }
}
