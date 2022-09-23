package lv.javaguru.java2.repo_men_inc.core.responses;

import java.util.List;

public class RemoveDebtorResponse extends CoreResponse{

    private boolean isDebtorRemoved;

    public RemoveDebtorResponse(List<CoreError> errors) {
        super(errors);
    }

    public RemoveDebtorResponse(boolean isDebtorRemoved) {
        this.isDebtorRemoved = isDebtorRemoved;
    }

    public boolean isDebtorRemoved() {
        return isDebtorRemoved;
    }
}
