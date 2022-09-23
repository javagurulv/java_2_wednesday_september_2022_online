package lv.javaguru.java2.repo_men_inc.core.responses;

import java.util.List;

public class AddDebtorResponse extends CoreResponse{

    private boolean isDebtorSavedToDatabase;

    public AddDebtorResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddDebtorResponse(boolean isDebtorSavedToDatabase) {
        this.isDebtorSavedToDatabase = isDebtorSavedToDatabase;
    }

    public boolean isDebtorSavedToDatabase() {
        return isDebtorSavedToDatabase;
    }
}
