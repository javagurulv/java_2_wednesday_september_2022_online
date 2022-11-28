package myApp.web_ui.controllers;

import myApp.core.requests.GetAllUsersRequest;
import myApp.core.responses.GetAllUsersResponse;
import myApp.core.services.GetAllUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetAllUsersController {

    @Autowired
    private GetAllUsersService service;


    @GetMapping(value = "/getAllUsers")
    public String getAllUsers(ModelMap modelMap) {
        GetAllUsersResponse response = service.execute(new GetAllUsersRequest());
        modelMap.addAttribute("users", response.getUsers());
        return "/getAllUsers";
    }
}
