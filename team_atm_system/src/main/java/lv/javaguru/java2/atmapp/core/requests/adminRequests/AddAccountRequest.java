package lv.javaguru.java2.atmapp.core.requests.adminRequests;

public class AddAccountRequest {

    private String userName;
    private int userId;

    public AddAccountRequest(String userName, int userId) {
        this.userName = userName;
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserId() {
        return userId;
    }
}
