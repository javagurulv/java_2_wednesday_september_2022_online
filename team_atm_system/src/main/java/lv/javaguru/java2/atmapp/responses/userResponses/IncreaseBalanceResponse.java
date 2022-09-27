package lv.javaguru.java2.atmapp.responses.userResponses;

import lv.javaguru.java2.atmapp.balanceServicesUI.Balance;

public class IncreaseBalanceResponse {
    private Balance balanceToIncrease;

    public IncreaseBalanceResponse(Balance balanceToIncrease) {
        this.balanceToIncrease = balanceToIncrease;
    }

    public void setBalanceToIncrease(Balance balanceToIncrease) {
        this.balanceToIncrease = balanceToIncrease;
    }
}
