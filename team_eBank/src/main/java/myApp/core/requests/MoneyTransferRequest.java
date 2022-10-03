package myApp.core.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MoneyTransferRequest {

    private String personalCode;
    private String anotherPersonalCode;
    private int value;
}
