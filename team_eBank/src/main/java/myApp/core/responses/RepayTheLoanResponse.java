package myApp.core.responses;

import lombok.Getter;

import java.util.List;

@Getter
public class RepayTheLoanResponse extends CoreResponse{

    boolean isLoanPaid;

    public RepayTheLoanResponse(boolean isLoanPaid) {
        this.isLoanPaid = isLoanPaid;
    }

    public RepayTheLoanResponse(List<CoreError> errors) {
        super(errors);
    }
}