package lv.javaguru.java2.tasksScheduler.core.responses;

import java.util.List;

public class ExitSettingsResponse extends CoreResponse {
    public ExitSettingsResponse() {
    }

    public ExitSettingsResponse(List<CoreError> errors) {
        super(errors);
    }
}
