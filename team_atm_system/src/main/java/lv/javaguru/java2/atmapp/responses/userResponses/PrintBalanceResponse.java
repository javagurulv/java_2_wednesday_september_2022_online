package lv.javaguru.java2.atmapp.responses.userResponses;

import lv.javaguru.java2.atmapp.balanceServicesUI.Balance;

public class PrintBalanceResponse {

    private Balance balance;

    public PrintBalanceResponse(Balance balance) {
        this.balance = balance;
    }

    public Balance getBalance() {
        return balance;
    }
}
