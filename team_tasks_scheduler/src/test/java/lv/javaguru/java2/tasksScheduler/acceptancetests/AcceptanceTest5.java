package lv.javaguru.java2.tasksScheduler.acceptancetests;

import lv.javaguru.java2.tasksScheduler.config.TaskSchedulerCoreConfig;
import lv.javaguru.java2.tasksScheduler.core.database.jpa.JpaSettingsRepository;
import lv.javaguru.java2.tasksScheduler.core.domain.Settings;
import lv.javaguru.java2.tasksScheduler.core.requests.*;
import lv.javaguru.java2.tasksScheduler.core.requests.ordering_paging.Ordering;
import lv.javaguru.java2.tasksScheduler.core.requests.ordering_paging.Paging;
import lv.javaguru.java2.tasksScheduler.core.responses.AddSettingsResponse;
import lv.javaguru.java2.tasksScheduler.core.responses.GetSettingsResponse;
import lv.javaguru.java2.tasksScheduler.core.responses.SearchTasksResponse;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.*;
import lv.javaguru.java2.tasksScheduler.core.services.system.CheckSettingsExistenceService;
import lv.javaguru.java2.tasksScheduler.core.services.system.GetSettingsService;
import lv.javaguru.java2.tasksScheduler.core.services.system.SessionService;
import lv.javaguru.java2.tasksScheduler.utils.Encryption;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TaskSchedulerCoreConfig.class})
@Sql({"/schema.sql"})
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
