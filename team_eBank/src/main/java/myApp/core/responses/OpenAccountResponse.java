package myApp.core.responses;

import java.util.List;

public class OpenAccountResponse extends CoreResponse {

    private boolean isCompleted;

    public OpenAccountResponse(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public OpenAccountResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isCompleted() {
        return isCompleted;
    }
}
