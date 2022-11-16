package generalPackage.core.requests.adminRequests;

public class GetAllAccountsRequest {


    Ordering ordering;

    public GetAllAccountsRequest() {

    }

    public GetAllAccountsRequest(Ordering ordering) {
        this.ordering = ordering;
    }

    public Ordering getOrdering() {
        return ordering;
    }
}

