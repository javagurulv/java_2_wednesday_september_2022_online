package lv.javaguru.java2.tasksScheduler.acceptancetests;

import lv.javaguru.java2.tasksScheduler.core.requests.*;
import lv.javaguru.java2.tasksScheduler.core.responses.*;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.*;
import lv.javaguru.java2.tasksScheduler.core.services.system.CheckSettingsExistenceService;
import lv.javaguru.java2.tasksScheduler.enums.MenuType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lv.javaguru.java2.tasksScheduler.config.TaskSchedulerCoreConfig;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TaskSchedulerCoreConfig.class})
@Sql({"/schema.sql"})
public class AcceptanceTest1 {

    @Autowired private AddSettingsService addSettingsService;
    @Autowired private UserRegistrationService userService;
    @Autowired private AddTaskService taskService;
    @Autowired private LoginService loginService;
    @Autowired private LogoutService logoutService;
    @Autowired private GetUsersService getUsersService;
    @Autowired private CheckSettingsExistenceService checkSettingsExistenceService;
    @Autowired private GetOutstandingTasksService getOutstandingTasksService;





    @Test
    public void shouldReturnCorrectSettingList() {
        AddSettingsRequest request = new AddSettingsRequest("AAAA", "AAAA@bbbb.com",
                "qwerty123", "smtp.xyz.com", "999", "ssl");
        addSettingsService.execute(request);
        CheckSettingsResponse checkSettingsResponse = checkSettingsExistenceService.execute(new CheckSettingsRequest());

        assertEquals(checkSettingsResponse.doesRecordExist(), true);
    }

    @Test
    public void shouldReturnCorrectUserList() {
        UserRegistrationRequest registrationRequest;
        GetUsersRequest getRequest;
        GetUsersResponse getResponse;

        registrationRequest = new UserRegistrationRequest("1111","1111",
                "a@b.c", false);
        userService.execute(registrationRequest);
        registrationRequest = new UserRegistrationRequest("2222","2222",
                "t@t.t", false);
        userService.execute(registrationRequest);
        registrationRequest = new UserRegistrationRequest("John Doe","2222",
                "c@b.a", false);
        userService.execute(registrationRequest);

        getRequest = new GetUsersRequest(null, null, null, null);
        getResponse  = getUsersService.execute(getRequest, MenuType.ADMIN);

        assertEquals(3, getResponse.getUsers().size());
    }

    @Test
    public void shouldReturnCorrectTaskList() {
        UserRegistrationRequest registrationRequest = new UserRegistrationRequest("1111",
                                            "1111","a@b.c", false);
        userService.execute(registrationRequest);


        loginService.execute(new LoginRequest("1111","1111"));

        AddTaskRequest request = new AddTaskRequest("first task task", 0,
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

        logoutService.execute(new LogoutRequest());


        loginService.execute(new LoginRequest("1111","1111"));
        GetOutstandingTasksRequest getTasksRequest = new GetOutstandingTasksRequest(LocalDateTime.MAX);
        GetOutstandingTasksResponse getTasksResponse = getOutstandingTasksService.execute(getTasksRequest);
        logoutService.execute(new LogoutRequest());

        assertEquals(3, getTasksResponse.getTasks().size());


    }


}
