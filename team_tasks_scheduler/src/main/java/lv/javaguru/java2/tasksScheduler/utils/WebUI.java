package lv.javaguru.java2.tasksScheduler.utils;

import lv.javaguru.java2.tasksScheduler.core.responses.GetCurrentUserResponse;
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
}
