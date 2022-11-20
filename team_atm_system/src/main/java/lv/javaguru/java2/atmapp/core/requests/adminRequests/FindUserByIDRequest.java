package lv.javaguru.java2.atmapp.core.requests.adminRequests;

public class FindUserByIDRequest {

    int userIDtoFind;

    public FindUserByIDRequest(int userIDtoFind) {
        this.userIDtoFind = userIDtoFind;
    }

    public int getUserIDtoFind() {
        return userIDtoFind;
    }
}
