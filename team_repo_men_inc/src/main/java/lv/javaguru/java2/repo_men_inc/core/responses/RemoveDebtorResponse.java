package lv.javaguru.java2.repo_men_inc.core.responses;

public class RemoveDebtorResponse {

    private boolean isDebtorRemoved;

    public RemoveDebtorResponse(boolean isDebtorRemoved) {
        this.isDebtorRemoved = isDebtorRemoved;
    }

    public boolean isDebtorRemoved() {
        return isDebtorRemoved;
    }
}
