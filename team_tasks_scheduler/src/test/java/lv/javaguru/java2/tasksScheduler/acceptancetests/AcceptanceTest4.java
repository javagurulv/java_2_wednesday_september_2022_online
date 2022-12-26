package lv.javaguru.java2.tasksScheduler.acceptancetests;

import lv.javaguru.java2.tasksScheduler.config.TaskSchedulerCoreConfig;
import lv.javaguru.java2.tasksScheduler.core.domain.Task;
import lv.javaguru.java2.tasksScheduler.core.domain.User;
import lv.javaguru.java2.tasksScheduler.core.requests.*;
import lv.javaguru.java2.tasksScheduler.core.requests.ordering_paging.Ordering;
import lv.javaguru.java2.tasksScheduler.core.responses.*;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.*;
import lv.javaguru.java2.tasksScheduler.core.services.system.GetCurrentUserService;
import lv.javaguru.java2.tasksScheduler.enums.MenuType;
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
public class AcceptanceTest4 {

    @Autowired private AddSettingsService addSettingsService;
    @Autowired private UserRegistrationService userService;
    @Autowired private AddTaskService taskService;
    @Autowired private LoginService loginService;
    @Autowired private LogoutService logoutService;
    @Autowired private GetUsersService getUsersService;
    @Autowired private SearchTasksService searchTasksService;
    @Autowired private DeleteCurrentUserService deleteCurrentUserService;
    @Autowired private AmendCurrentUserService amendCurrentUserService;
    @Autowired private GetCurrentUserService getCurrentUserService;
    @Autowired private DeleteTaskService deleteTaskService;
    @Autowired private AmendTaskService amendTaskService;





    @Test
    public void shouldDeleteUser() {
        UserRegistrationRequest registrationRequest;
        GetUsersRequest getRequest;
        GetUsersResponse getResponse;

        registrationRequest = new UserRegistrationRequest("user1","1111",
                "user1@b.c", false);
        userService.execute(registrationRequest);
        registrationRequest = new UserRegistrationRequest("user2","2222",
                "user2@t.t", false);
        userService.execute(registrationRequest);
        registrationRequest = new UserRegistrationRequest("user3","3333",
                "user3@b.a", false);
        userService.execute(registrationRequest);


        loginService.execute(new LoginRequest("user3","3333"));
        DeleteCurrentUserRequest request = new DeleteCurrentUserRequest();
        DeleteCurrentUserResponse response = deleteCurrentUserService.execute(request);



        getRequest = new GetUsersRequest("user", null, null, null);
        getResponse  = getUsersService.execute(getRequest, MenuType.ADMIN);

        assertEquals(2, getResponse.getUsers().size());
        assertEquals("user1", getResponse.getUsers().get(0).getUsername());
        assertEquals("user1@b.c", getResponse.getUsers().get(0).getEmail());
        assertEquals("user2", getResponse.getUsers().get(1).getUsername());
        assertEquals("user2@t.t", getResponse.getUsers().get(1).getEmail());
    }

    @Test
    public void shouldAmendUser() {
        UserRegistrationRequest registrationRequest;
        GetUsersRequest getRequest;
        GetUsersResponse getResponse;
        AmendCurrentUserRequest amendRequest;
        AmendCurrentUserResponse amendResponse;
        GetCurrentUserRequest getCurrentUserRequest;
        GetCurrentUserResponse getCurrentUserResponse;
        User currentUser, newUser;


        registrationRequest = new UserRegistrationRequest("user1","1111",
                "user1@b.c", false);
        userService.execute(registrationRequest);
        registrationRequest = new UserRegistrationRequest("user2","2222",
                "user2@t.t", false);
        userService.execute(registrationRequest);
        registrationRequest = new UserRegistrationRequest("user3","3333",
                "user3@b.a", false);
        userService.execute(registrationRequest);


        loginService.execute(new LoginRequest("user3","3333"));
        getCurrentUserRequest = new GetCurrentUserRequest(true);
        getCurrentUserResponse = getCurrentUserService.execute(getCurrentUserRequest);

        currentUser = getCurrentUserResponse.getUser();
        newUser = new User("userUser","qwerty","user1user@b.c", true);
        newUser.setId(currentUser.getId());

        amendRequest = new AmendCurrentUserRequest(newUser);
        amendCurrentUserService.execute(amendRequest);
        logoutService.execute(new LogoutRequest());


        getRequest = new GetUsersRequest("userUser", null, null, null);
        getResponse  = getUsersService.execute(getRequest, MenuType.ADMIN);


        assertEquals(1, getResponse.getUsers().size());
        assertEquals("userUser", getResponse.getUsers().get(0).getUsername());
        assertEquals("user1user@b.c", getResponse.getUsers().get(0).getEmail());
    }

    @Test
    public void shouldDeleteTask() {

        AddTaskRequest request;
        UserRegistrationRequest registrationRequest;
        SearchTasksRequest getTasksRequest;
        SearchTasksResponse getTasksResponse;
        DeleteTaskRequest deleteRequest;
        Ordering order;


        registrationRequest= new UserRegistrationRequest("1111",
                "1111","a@b.c", false);
        userService.execute(registrationRequest);


        loginService.execute(new LoginRequest("1111","1111"));

        request = new AddTaskRequest("1st task task", 0,
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
        order = new Ordering("description", "ascending");
        getTasksRequest = new SearchTasksRequest("tas", order);
        getTasksResponse = searchTasksService.execute(getTasksRequest);
        logoutService.execute(new LogoutRequest());


        loginService.execute(new LoginRequest("1111","1111"));
        deleteRequest = new DeleteTaskRequest(getTasksResponse.getTasks().get(0).getId());
        deleteTaskService.execute(deleteRequest);
        logoutService.execute(new LogoutRequest());

        loginService.execute(new LoginRequest("1111","1111"));
        order = new Ordering("description", "ascending");
        getTasksRequest = new SearchTasksRequest("tas", order);
        getTasksResponse = searchTasksService.execute(getTasksRequest);
        logoutService.execute(new LogoutRequest());


        assertEquals(2, getTasksResponse.getTasks().size());
        assertEquals("2nd task task", getTasksResponse.getTasks().get(0).getDescription());
        assertEquals("3rd task task task", getTasksResponse.getTasks().get(1).getDescription());

    }

    @Test
    public void shouldAmendTask() {
        AddTaskRequest request;
        UserRegistrationRequest registrationRequest;
        SearchTasksRequest getTasksRequest;
        SearchTasksResponse getTasksResponse;
        Ordering order;
        AmendTaskRequest amendRequest;


        registrationRequest= new UserRegistrationRequest("1111",
                "1111","a@b.c", false);
        userService.execute(registrationRequest);


        loginService.execute(new LoginRequest("1111","1111"));

        request = new AddTaskRequest("1st task task", 0,
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
        order = new Ordering("description", "ascending");
        getTasksRequest = new SearchTasksRequest("tas", order);
        getTasksResponse = searchTasksService.execute(getTasksRequest);
        logoutService.execute(new LogoutRequest());

        loginService.execute(new LoginRequest("1111","1111"));
        Task amendedTask = new Task(getTasksResponse.getTasks().get(0));
        amendedTask.setDescription("new task description");
        amendedTask.setRegularity(999);
        amendRequest = new AmendTaskRequest(amendedTask);
        amendTaskService.execute(amendRequest);
        logoutService.execute(new LogoutRequest());

        loginService.execute(new LoginRequest("1111","1111"));
        order = new Ordering("description", "ascending");
        getTasksRequest = new SearchTasksRequest("new", order);
        getTasksResponse = searchTasksService.execute(getTasksRequest);
        logoutService.execute(new LogoutRequest());

        assertEquals(1, getTasksResponse.getTasks().size());
        assertEquals("new task description", getTasksResponse.getTasks().get(0).getDescription());
        assertEquals(999, getTasksResponse.getTasks().get(0).getRegularity());
    }

}
