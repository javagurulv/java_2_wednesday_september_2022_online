package lv.javaguru.java2.tasksScheduler.responses;

import java.util.List;

public class LogoutResponse extends CoreResponse {
    public LogoutResponse() {
    }

    public LogoutResponse(List<CoreError> errors) {
        super(errors);
    }
}
