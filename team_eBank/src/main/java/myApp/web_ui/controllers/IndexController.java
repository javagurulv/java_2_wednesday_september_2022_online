package myApp.web_ui.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(value = "/admin")
    public String index() {
        return "indexAdmin";
    }

    @GetMapping(value = "/user")
    public String user() {
        return "indexUser";
    }
}

