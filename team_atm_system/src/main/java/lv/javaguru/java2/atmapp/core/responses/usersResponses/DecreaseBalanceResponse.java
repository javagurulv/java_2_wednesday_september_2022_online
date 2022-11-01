package lv.javaguru.java2.atmapp.core.responses.usersResponses;

import java.util.List;

public class DecreaseBalanceResponse extends CoreResponseUsers {

    private boolean withdrawMoneySuccessful;

    public DecreaseBalanceResponse(boolean withdrawMoneySuccessful) {
        this.withdrawMoneySuccessful = withdrawMoneySuccessful;
    }

    public boolean isMoneyWithdraw(){
        return withdrawMoneySuccessful;
    }

    public DecreaseBalanceResponse (List<CoreErrorUsers> errors){
        super(errors);
    }
}
