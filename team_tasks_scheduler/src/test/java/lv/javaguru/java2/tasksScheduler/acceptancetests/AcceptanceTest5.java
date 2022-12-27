package lv.javaguru.java2.tasksScheduler.acceptancetests;

import lv.javaguru.java2.tasksScheduler.config.TaskSchedulerCoreConfig;
import lv.javaguru.java2.tasksScheduler.core.database.jpa.JpaSettingsRepository;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.*;
import lv.javaguru.java2.tasksScheduler.core.services.system.CheckSettingsExistenceService;
import lv.javaguru.java2.tasksScheduler.core.services.system.GetSettingsService;
import lv.javaguru.java2.tasksScheduler.core.services.system.SessionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TaskSchedulerCoreConfig.class})
@Sql({"/__schema.sql"})
public class AcceptanceTest5 {

    @Autowired private AddSettingsService addSettingsService;
    @Autowired private UserRegistrationService userService;
    @Autowired private AddTaskService taskService;
    @Autowired private LoginService loginService;
    @Autowired private LogoutService logoutService;
    @Autowired private GetUsersService getUsersService;
    @Autowired private CheckSettingsExistenceService checkSettingsExistenceService;
    @Autowired private GetOutstandingTasksService getOutstandingTasksService;
    @Autowired private SearchTasksService searchTasksService;
    @Autowired private SessionService sessionService;
    @Autowired private GetSettingsService getSettingsService;
    @Autowired private JpaSettingsRepository settingsRepository;

    @Test
    public void shouldBe() {

    }

}
