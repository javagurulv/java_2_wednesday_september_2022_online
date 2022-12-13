package lv.javaguru.java2.tasksScheduler.core.responses;

import java.util.List;

public class ExitResponse extends CoreResponse {
    public ExitResponse() {
    }

    public ExitResponse(List<CoreError> errors) {
        super(errors);
    }
}
