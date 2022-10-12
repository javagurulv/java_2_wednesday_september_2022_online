package generalPackage.usersRequests;

public class PrintBalanceRequest {

    int userIDtoGetBalance;

    public PrintBalanceRequest(int userIDtoGetBalance) {
        this.userIDtoGetBalance = userIDtoGetBalance;
    }

    public int getUserIDtoGetBalance() {
        return userIDtoGetBalance;
    }
}
