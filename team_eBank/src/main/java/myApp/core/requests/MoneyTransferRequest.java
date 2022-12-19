package myApp.core.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MoneyTransferRequest {

    private String personalCode;
    private String anotherPersonalCode;
    private int value;

    public MoneyTransferRequest(String anotherPersonalCode, int value) {
        this.anotherPersonalCode = anotherPersonalCode;
        this.value = value;
    }
}
