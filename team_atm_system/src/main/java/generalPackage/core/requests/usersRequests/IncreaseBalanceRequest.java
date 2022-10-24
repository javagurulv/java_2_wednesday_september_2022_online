package generalPackage.core.requests.usersRequests;

public class IncreaseBalanceRequest {

    private int userID;
    private int amountToIncrease;

    public IncreaseBalanceRequest(int userID, int amountToIncrease) {
        this.userID = userID;
        this.amountToIncrease = amountToIncrease;
    }

    public int getUserID() {
        return userID;
    }

    public int getAmountToIncrease() {
        return amountToIncrease;
    }
}
