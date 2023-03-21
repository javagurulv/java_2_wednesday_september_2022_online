package lv.javaguru.java2.tasksScheduler.utils;

import lv.javaguru.java2.tasksScheduler.core.requests.CheckLoggedUserRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.GetCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.CheckLoggedUserResponse;
import lv.javaguru.java2.tasksScheduler.core.responses.GetCurrentUserResponse;
import lv.javaguru.java2.tasksScheduler.core.services.system.CheckLoggedUserService;
import lv.javaguru.java2.tasksScheduler.core.services.system.GetCurrentUserService;
import org.springframework.ui.ModelMap;

public class WebUI {
    public static String getGreeting(String name) {
        String greeting;
        if (ValueChecking.stringIsEmpty(name)) {
            greeting = "Welcome to Your account!";
        } else {
            greeting = "Welcome, " + name + "!";
        }
        return greeting;
    }

    public static void addToPageUserGreeting(ModelMap modelMap,
                                          GetCurrentUserResponse response) {
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("greeting",
                    WebUI.getGreeting(response.getUser().getUsername()));
            modelMap.addAttribute("request", response.getUser());
        }
    }

    public static boolean checkIfUserIsLoggedIn(CheckLoggedUserService service, String sessionId) {
        CheckLoggedUserRequest request = new CheckLoggedUserRequest(sessionId);
        CheckLoggedUserResponse response = service.execute(request);
        return response.isUserLoggedIn();
    }

    public static void addToPageUserGreeting(GetCurrentUserService service, ModelMap modelMap, String sessionId) {
        GetCurrentUserRequest request = new GetCurrentUserRequest(true, sessionId);
        GetCurrentUserResponse response = service.execute(request);
        addToPageUserGreeting(modelMap, response);
    }
}
