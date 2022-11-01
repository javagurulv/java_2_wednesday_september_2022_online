package generalPackage.core.requests.adminRequests;

public class SearchAccountsServiceRequest {

    String userNameToFind;

    public SearchAccountsServiceRequest(String userNameToFind) {
        this.userNameToFind = userNameToFind;
    }

    public String getUserNameToFind() {
        return userNameToFind;
    }
}
