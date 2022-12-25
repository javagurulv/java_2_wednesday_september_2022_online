package myApp.core.responses;

import lombok.Getter;
import myApp.core.domain.BankAccount;
import myApp.core.domain.User;

import java.util.List;

@Getter
public class AddUserResponse extends CoreResponse {

    private User user;

    public AddUserResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddUserResponse(User user) {
        this.user = user;
    }
}
