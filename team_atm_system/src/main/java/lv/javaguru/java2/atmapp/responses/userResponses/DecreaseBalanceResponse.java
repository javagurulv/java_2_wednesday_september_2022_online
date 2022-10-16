package lv.javaguru.java2.atmapp.responses.userResponses;

import lv.javaguru.java2.atmapp.balanceServicesUI.Balance;

public class DecreaseBalanceResponse {

    private Balance balanceToDecrease;

    public DecreaseBalanceResponse(Balance balanceToDecrease) {
        this.balanceToDecrease = balanceToDecrease;
    }

    public void setBalanceToDecrease(Balance balanceToDecrease) {
        this.balanceToDecrease = balanceToDecrease;
    }
}
