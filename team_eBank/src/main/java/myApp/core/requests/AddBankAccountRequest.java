package myApp.core.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddBankAccountRequest {

    private String name;
    private String surname;
    private String personalCode;
    private String password;

}
