package lv.javaguru.java2.tasksScheduler.acceptancetests;

import lv.javaguru.java2.tasksScheduler.core.services.menu_services.*;
import lv.javaguru.java2.tasksScheduler.enums.MenuType;
import lv.javaguru.java2.tasksScheduler.core.requests.AddSettingsRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.GetUsersRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.UserRegistrationRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.AddSettingsResponse;
import lv.javaguru.java2.tasksScheduler.core.responses.GetUsersResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lv.javaguru.java2.tasksScheduler.config.TaskSchedulerCoreConfig;

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


    @Test
    public void shouldReturnCorrectSettingList() {
        AddSettingsRequest request = new AddSettingsRequest("AAAA", "AAAA@bbbb.com",
                "qwerty123", "smtp.xyz.com", "999", "ssl");
        AddSettingsResponse response = addSettingsService.execute(request);
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
        assertEquals(getResponse.getUsers().size(), 3);

    }

    @Test
    public void shouldReturnCorrectTaskList() {

    }


}
