package lv.javaguru.java2.atmapp.requests.userRequests;

public class DecreaseBalanceRequest {

    private int balanceToDecrease;

    public DecreaseBalanceRequest(int balanceToDecrease) {
        this.balanceToDecrease = balanceToDecrease;
    }

    public int getBalanceToDecrease() {
        return balanceToDecrease;
    }
}
