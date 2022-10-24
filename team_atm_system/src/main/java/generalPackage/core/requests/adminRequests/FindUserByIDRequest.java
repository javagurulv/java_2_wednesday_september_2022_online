package generalPackage.core.requests.adminRequests;

public class FindUserByIDRequest {

    int userIDtoFind;

    public FindUserByIDRequest(int userIDtoFind) {
        this.userIDtoFind = userIDtoFind;
    }

    public int getUserIDtoFind() {
        return userIDtoFind;
    }
}
