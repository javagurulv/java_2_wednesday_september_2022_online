package lv.javaguru.java2.tasksScheduler.requests;

import lv.javaguru.java2.tasksScheduler.requests.ordering_paging.Ordering;
import lv.javaguru.java2.tasksScheduler.requests.ordering_paging.Paging;
import lv.javaguru.java2.tasksScheduler.utils.ValueChecking;

public class GetUsersRequest {

    private String username;
    private String email;

    private Ordering ordering;
    private Paging paging;

    public GetUsersRequest() {
    }

    public GetUsersRequest(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public GetUsersRequest(String username, String email, Ordering ordering) {
        this.username = username;
        this.email = email;
        this.ordering = ordering;
    }

    public GetUsersRequest(String username, String email, Paging paging) {
        this.username = username;
        this.email = email;
        this.paging = paging;
    }

    public GetUsersRequest(String username, String email, Ordering ordering, Paging paging) {
        this.username = username;
        this.email = email;
        this.ordering = ordering;
        this.paging = paging;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public Paging getPaging() {
        return paging;
    }

    public boolean isUsernameProvided() {
        return !ValueChecking.stringIsEmpty(this.username);
    }

    public boolean isEmailProvided() {
        return !ValueChecking.stringIsEmpty(this.email);
    }
}
