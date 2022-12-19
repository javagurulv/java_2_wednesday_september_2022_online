package lv.javaguru.java2.tasksScheduler.core.database;

import lv.javaguru.java2.tasksScheduler.core.domain.Settings;

public interface SettingsRepository {

    boolean save(Settings settings);

    boolean recordExists();

    boolean passwordIsValid(String password);

    Settings getRecord();
}
