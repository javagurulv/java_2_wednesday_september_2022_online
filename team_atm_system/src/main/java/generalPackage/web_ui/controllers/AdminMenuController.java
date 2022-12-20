package generalPackage.web_ui.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminMenuController {

    @GetMapping (value = "/adminMenu")
    public String adminMenu(){
        return "adminMenu";
    }
}
