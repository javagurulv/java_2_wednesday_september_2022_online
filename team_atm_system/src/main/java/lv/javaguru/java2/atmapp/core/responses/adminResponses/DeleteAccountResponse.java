package lv.javaguru.java2.atmapp.core.responses.adminResponses;

import java.util.List;

public class DeleteAccountResponse extends CoreResponse {

    private boolean accountDeleted;

    public DeleteAccountResponse(boolean accountDeleted) {
        this.accountDeleted = accountDeleted;
    }

    public boolean isAccountDeleted() {
        return accountDeleted;
    }

    public DeleteAccountResponse (List<CoreError> errors){
        super(errors);
    }
}
