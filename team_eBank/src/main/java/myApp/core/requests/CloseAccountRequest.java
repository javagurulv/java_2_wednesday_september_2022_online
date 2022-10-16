package myApp.core.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CloseAccountRequest {

    private String personalCode;
}
