package lv.javaguru.java2.atmapp.requests.userRequests;

public class IncreaseBalanceRequest {

    private int balance;

    public IncreaseBalanceRequest(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }
}
