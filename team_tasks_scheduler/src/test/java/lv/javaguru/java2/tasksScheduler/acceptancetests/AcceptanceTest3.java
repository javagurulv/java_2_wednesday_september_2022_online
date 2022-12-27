package lv.javaguru.java2.tasksScheduler.acceptancetests;

import lv.javaguru.java2.tasksScheduler.config.TaskSchedulerCoreConfig;
import lv.javaguru.java2.tasksScheduler.core.database.jpa.JpaSettingsRepository;
import lv.javaguru.java2.tasksScheduler.core.domain.Settings;
import lv.javaguru.java2.tasksScheduler.core.requests.*;
import lv.javaguru.java2.tasksScheduler.core.requests.ordering_paging.Ordering;
import lv.javaguru.java2.tasksScheduler.core.requests.ordering_paging.Paging;
import lv.javaguru.java2.tasksScheduler.core.responses.*;
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
@Sql({"/__schema.sql"})
public class AcceptanceTest3 {

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
    public void shouldFindCorrectTaskCountWithPaging() {
        AddTaskRequest request;
        UserRegistrationRequest registrationRequest;
        SearchTasksRequest getTasksRequest;
        SearchTasksResponse getTasksResponse;
        Ordering order;
        Paging pages;

        registrationRequest= new UserRegistrationRequest("1111",
                "1111","a@b.c", false);
        userService.execute(registrationRequest);


        loginService.execute(new LoginRequest("1111","1111"));

        request = new AddTaskRequest("first task task", 0,
                LocalDateTime.of(2023,9,12,9,4),
                LocalDateTime.of(2023,11,12,9,5));
        taskService.execute(request);
        request = new AddTaskRequest("2nd task task", 2,
                LocalDateTime.of(2023,11,30,8,10),
                LocalDateTime.of(2023,12,1,20,40));
        taskService.execute(request);
        request = new AddTaskRequest("3rd task task task", 0,
                LocalDateTime.of(2023,7,30,8,10),
                LocalDateTime.of(2023,12,30,8,10));
        taskService.execute(request);
        request = new AddTaskRequest("check joga classes.", 2,
                LocalDateTime.of(2023,12,30,8,10),
                LocalDateTime.of(2027,12,1,20,40));
        taskService.execute(request);
        request = new AddTaskRequest("check joga classes. restart", 2,
                LocalDateTime.of(2023,12,30,8,10),
                LocalDateTime.of(2028,10,1,20,40));
        taskService.execute(request);
        request = new AddTaskRequest("check joga classes. new restart", 2,
                LocalDateTime.of(2023,12,30,8,10),
                LocalDateTime.of(2029,10,1,20,40));
        taskService.execute(request);
        request = new AddTaskRequest("check joga classes. new new restart", 2,
                LocalDateTime.of(2023,12,30,8,10),
                LocalDateTime.of(2030,10,1,20,40));
        taskService.execute(request);

        logoutService.execute(new LogoutRequest());


        loginService.execute(new LoginRequest("1111","1111"));
        order = new Ordering("end date", "ascending");
        pages = new Paging(1, 3); //3 tasks on the page
        getTasksRequest = new SearchTasksRequest("joga", order, pages);
        getTasksResponse = searchTasksService.execute(getTasksRequest);
        logoutService.execute(new LogoutRequest());

        assertEquals(3, getTasksResponse.getTasks().size());
        assertEquals("check joga classes.",
                getTasksResponse.getTasks().get(0).getDescription());
        assertEquals("check joga classes. restart",
                getTasksResponse.getTasks().get(1).getDescription());
        assertEquals("check joga classes. new restart",
                getTasksResponse.getTasks().get(2).getDescription());

    }

    @Test
    public void shouldReturnCorrectSettings() {

        Settings settings = new Settings("admin", "adminadmin@mail.com", "qwerty123",
                "smtp.mail.com", "666", "ssl");

        AddSettingsRequest request = new AddSettingsRequest(settings.getAdminPassword(),
                                                                settings.getEmailFrom(),
                                                            settings.getEmailPassword(),
                                                                settings.getEmailHost(),
                                                                settings.getEmailPort(),
                                                            settings.getEmailProtocol());
        AddSettingsResponse response = addSettingsService.execute(request);

        sessionService.login(0L, "admin");
        GetSettingsResponse getSettingsResponse = getSettingsService.execute(new GetSettingsRequest(true));
        Settings settingsReceived = getSettingsResponse.getSettings();

        assertEquals(settings, settingsReceived);

    }

    @Test
    public void shouldBeCorrectPassword() {
        Settings settings = new Settings("admin", "adminadmin@mail.com",
                                "qwerty123","smtp.mail.com",
                                                "666", "ssl");

        AddSettingsRequest request = new AddSettingsRequest(settings.getAdminPassword(),
                                                                settings.getEmailFrom(),
                                                                settings.getEmailPassword(),
                                                                settings.getEmailHost(),
                                                                settings.getEmailPort(),
                                                                settings.getEmailProtocol());
        AddSettingsResponse response = addSettingsService.execute(request);

        boolean result = settingsRepository.passwordIsValid(Encryption.stringHashing("admin"));

        assertEquals(true, result);
    }

    @Test
    public void shouldBeIncorrectPassword (){
        Settings settings = new Settings("admin", "adminadmin@mail.com",
                "qwerty123","smtp.mail.com",
                "666", "ssl");

        AddSettingsRequest request = new AddSettingsRequest(settings.getAdminPassword(),
                                                                  settings.getEmailFrom(),
                                                                settings.getEmailPassword(),
                                                                    settings.getEmailHost(),
                                                                    settings.getEmailPort(),
                                                                settings.getEmailProtocol());
        AddSettingsResponse response = addSettingsService.execute(request);

        boolean result = settingsRepository.passwordIsValid(Encryption.stringHashing("notAdmin"));

        assertEquals(false, result);
    }

}
