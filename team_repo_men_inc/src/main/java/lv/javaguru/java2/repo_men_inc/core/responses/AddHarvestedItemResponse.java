package lv.javaguru.java2.repo_men_inc.core.responses;

public class AddHarvestedItemResponse {

    private boolean isHarvestedItemAdded;

    public AddHarvestedItemResponse(boolean isHarvestedItemAdded) {
        this.isHarvestedItemAdded = isHarvestedItemAdded;
    }

    public boolean isHarvestedItemAdded() {
        return isHarvestedItemAdded;
    }
}
