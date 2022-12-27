package generalPackage.core.requests.usersRequests;

public class PrintBalanceRequest {

    int userIDtoGetBalance;

    public PrintBalanceRequest() {
    }

    public PrintBalanceRequest(int userIDtoGetBalance) {
        this.userIDtoGetBalance = userIDtoGetBalance;
    }

    public int getUserIDtoGetBalance() {
        return userIDtoGetBalance;
    }
}
