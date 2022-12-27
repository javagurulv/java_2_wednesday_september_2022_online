package lv.javaguru.java2.tasksScheduler.core.database.jpa;

import lv.javaguru.java2.tasksScheduler.core.domain.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaSettingsRepository extends JpaRepository<Settings, Long>, JpaSettingsCustomRep {

}
