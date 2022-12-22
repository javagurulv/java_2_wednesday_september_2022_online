package myApp.core.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddBankAccountRequest {

    private String name;
    private String surname;
    private String personalCode;

}
