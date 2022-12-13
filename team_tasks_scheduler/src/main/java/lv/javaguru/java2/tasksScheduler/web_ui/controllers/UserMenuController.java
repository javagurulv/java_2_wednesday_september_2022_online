package lv.javaguru.java2.tasksScheduler.web_ui.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserMenuController {

    @GetMapping(value = "/userMenu")
    public String userMenu() {
        return "userMenu";
    }
}
