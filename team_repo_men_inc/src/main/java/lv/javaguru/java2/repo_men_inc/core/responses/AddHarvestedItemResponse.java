package lv.javaguru.java2.repo_men_inc.core.responses;

import java.util.List;

public class AddHarvestedItemResponse extends CoreResponse{

    private boolean isHarvestedItemAdded;

    public AddHarvestedItemResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddHarvestedItemResponse(boolean isHarvestedItemAdded) {
        this.isHarvestedItemAdded = isHarvestedItemAdded;
    }

    public boolean isHarvestedItemAdded() {
        return isHarvestedItemAdded;
    }
}
