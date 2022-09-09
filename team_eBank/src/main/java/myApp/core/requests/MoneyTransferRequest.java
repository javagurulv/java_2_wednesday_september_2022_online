package myApp.core.requests;

public class MoneyTransferRequest {

    private Long userID;
    private int value;
    private Long anotherAccountID;

    public MoneyTransferRequest(Long userID, int value, Long anotherAccountID) {
        this.userID = userID;
        this.value = value;
        this.anotherAccountID = anotherAccountID;
    }

    public Long getUserID() {
        return userID;
    }

    public int getValue() {
        return value;
    }

    public Long getAnotherAccountID() {
        return anotherAccountID;
    }
}
