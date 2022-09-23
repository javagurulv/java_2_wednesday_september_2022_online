package lv.javaguru.java2.repo_men_inc.core.requests;

public class AddHarvestedItemRequest {

    private Long debtorsId;
    private String harvestedItem;

    public AddHarvestedItemRequest(Long debtorsId, String harvestedItem) {
        this.debtorsId = debtorsId;
        this.harvestedItem = harvestedItem;
    }

    public Long getDebtorsId() {
        return debtorsId;
    }

    public String getHarvestedItem() {
        return harvestedItem;
    }
}
