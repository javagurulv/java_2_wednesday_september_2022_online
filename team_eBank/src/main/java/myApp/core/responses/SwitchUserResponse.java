package myApp.core.responses;


import lombok.Getter;

import java.util.List;
@Getter
public class SwitchUserResponse extends CoreResponse {
    private String personalCode;

    public SwitchUserResponse(String personalCode) {
        this.personalCode = personalCode;
    }

    public SwitchUserResponse(List<CoreError> errors) {
        super(errors);
    }
}
