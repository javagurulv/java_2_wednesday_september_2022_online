package myApp.core.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddBankAccountRequest {

    private String name;
    private String surname;
    private String personalCode;

}
