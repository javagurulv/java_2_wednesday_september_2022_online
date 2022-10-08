package lv.javaguru.java2.tasksScheduler.database;

import lv.javaguru.java2.tasksScheduler.domain.Settings;

public interface SettingsRepository {

    boolean save(Settings settings);

    boolean update(Settings settings);

    boolean recordExists();

    boolean passwordIsValid(String password);

    Settings getRecord();
}
