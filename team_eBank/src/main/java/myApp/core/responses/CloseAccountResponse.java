package myApp.core.responses;

import java.util.List;

public class CloseAccountResponse extends CoreResponse {

    private boolean isDeleted;
    private List<CoreError> errors;

    public CloseAccountResponse(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public CloseAccountResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
