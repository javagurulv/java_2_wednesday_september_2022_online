package myApp.core.responses;

import java.util.List;

public class RemoveBankAccountResponse extends CoreResponse {

    private boolean isDeleted;

    public RemoveBankAccountResponse(List<CoreError> errors) {
        super(errors);
    }

    public RemoveBankAccountResponse(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
