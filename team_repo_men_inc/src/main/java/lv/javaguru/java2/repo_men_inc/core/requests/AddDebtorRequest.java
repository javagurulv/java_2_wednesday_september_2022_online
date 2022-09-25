package lv.javaguru.java2.repo_men_inc.core.requests;

public class AddDebtorRequest {
    private String name;

    public AddDebtorRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
