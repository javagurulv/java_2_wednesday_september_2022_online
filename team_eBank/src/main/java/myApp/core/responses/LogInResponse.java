package myApp.core.responses;

import java.util.List;

public class LogInResponse extends CoreResponse {

    private String personalCode;

    public LogInResponse(String personalCode) {
        this.personalCode = personalCode;
    }

    public LogInResponse(List<CoreError> errors) {
        super(errors);
    }

    public String getPersonalCode() {
        return personalCode;
    }
}
