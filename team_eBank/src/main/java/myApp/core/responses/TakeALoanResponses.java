package myApp.core.responses;

import lombok.Getter;

import java.util.List;

@Getter
public class TakeALoanResponses extends CoreResponse {

    private boolean isLoanSucceed;

    public TakeALoanResponses(List<CoreError> errors) {
        super(errors);
    }

    public TakeALoanResponses(boolean isLoanSucceed) {
        this.isLoanSucceed = isLoanSucceed;
    }
}