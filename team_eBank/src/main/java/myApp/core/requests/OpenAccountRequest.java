package myApp.core.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OpenAccountRequest {

    private String personalCode;
    private Integer amount;
}
