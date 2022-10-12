package generalPackage.usersRequests;

public class DecreaseBalanceRequest {

    private int userID;
    private int amountToDecrease;

    public DecreaseBalanceRequest(int userID, int amountToDecrease) {
        this.userID = userID;
        this.amountToDecrease = amountToDecrease;
    }

    public int getUserID() {
        return userID;
    }

    public int getAmountToDecrease() {
        return amountToDecrease;
    }
}
