package lv.javaguru.java2.tasksScheduler.web_ui.controllers;

import lv.javaguru.java2.tasksScheduler.enums.MenuType;
import lv.javaguru.java2.tasksScheduler.requests.GetUsersRequest;
import lv.javaguru.java2.tasksScheduler.responses.GetUsersResponse;
import lv.javaguru.java2.tasksScheduler.services.menu_services.GetUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetUsersNamesCntrllr {
    @Autowired
    private GetUsersService getUsersService;

    @GetMapping(value = "/showUsernamesRegistered")
    public String showUsernamesRegisteredOnSystem(ModelMap modelMap) {
        GetUsersRequest request = new GetUsersRequest(null, null, null, null);
        GetUsersResponse response = getUsersService.execute(request, MenuType.ADMIN);

        modelMap.addAttribute("users", response.getUsersNames());
        return "/showUsernamesRegistered";
    }
}
