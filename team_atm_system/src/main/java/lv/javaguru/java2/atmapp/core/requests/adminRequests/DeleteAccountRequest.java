package lv.javaguru.java2.atmapp.core.requests.adminRequests;

public class DeleteAccountRequest {

int userIDtoDelete;

    public DeleteAccountRequest(int userIDtoDelete) {
        this.userIDtoDelete = userIDtoDelete;
    }

    public int getUserIDtoDelete() {
        return userIDtoDelete;
    }
}
