package lv.javaguru.java2.tasksScheduler.core.database.jpa;

import lv.javaguru.java2.tasksScheduler.core.domain.Settings;

public interface JpaSettingsCustomRep {
    boolean recordExists();

    boolean passwordIsValid(String password);

    Settings getRecord();
}
