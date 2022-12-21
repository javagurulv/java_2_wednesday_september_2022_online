package generalPackage.web_ui.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersMenuController {

    @GetMapping(value = "/usersMenu")
    public String usersMenu() {
        return "usersMenu";
    }
}
