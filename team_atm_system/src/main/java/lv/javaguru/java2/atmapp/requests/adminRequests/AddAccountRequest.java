package lv.javaguru.java2.atmapp.requests.adminRequests;

public class AddAccountRequest {

    private String name;

    private int userID;

    public AddAccountRequest(String name, int userID) {
        this.name = name;
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public int getUserID() {
        return userID;
    }
}
