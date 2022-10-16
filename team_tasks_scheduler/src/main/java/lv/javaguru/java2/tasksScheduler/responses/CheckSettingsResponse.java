package lv.javaguru.java2.tasksScheduler.responses;

import java.util.List;

public class CheckSettingsResponse extends CoreResponse{
    boolean doesRecordExist;

    public CheckSettingsResponse(List<CoreError> errors) {
        super(errors);
    }
    public CheckSettingsResponse(boolean doesRecordExist) {
        this.doesRecordExist = doesRecordExist;
    }

    public boolean doesRecordExist() {
        return doesRecordExist;
    }
}
