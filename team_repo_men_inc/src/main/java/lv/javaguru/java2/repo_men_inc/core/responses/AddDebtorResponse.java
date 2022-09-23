package lv.javaguru.java2.repo_men_inc.core.responses;

public class AddDebtorResponse {

    private boolean isDebtorSavedToDatabase;

    public AddDebtorResponse(boolean isDebtorSavedToDatabase) {
        this.isDebtorSavedToDatabase = isDebtorSavedToDatabase;
    }

    public boolean isDebtorSavedToDatabase() {
        return isDebtorSavedToDatabase;
    }
}
