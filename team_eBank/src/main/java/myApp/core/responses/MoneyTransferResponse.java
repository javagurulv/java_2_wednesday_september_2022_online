package myApp.core.responses;

import java.util.List;

public class MoneyTransferResponse extends CoreResponse {

     private boolean isTransactionSucceed;

    public MoneyTransferResponse(boolean isTransactionSucceed) {
        this.isTransactionSucceed = isTransactionSucceed;
    }

    public MoneyTransferResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isTransactionSucceed() {
        return isTransactionSucceed;
    }
}
