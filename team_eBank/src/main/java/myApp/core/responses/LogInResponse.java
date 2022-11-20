package myApp.core.responses;

import lombok.Getter;

import java.util.List;

@Getter
public class LogInResponse extends CoreResponse {

    private String personalCode;

    public LogInResponse(String personalCode) {
        this.personalCode = personalCode;
    }

    public LogInResponse(List<CoreError> errors) {
        super(errors);
    }


}
