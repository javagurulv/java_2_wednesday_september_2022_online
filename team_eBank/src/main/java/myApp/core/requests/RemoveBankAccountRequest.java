package myApp.core.requests;

public class RemoveBankAccountRequest {

    private Long id;

    public RemoveBankAccountRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
