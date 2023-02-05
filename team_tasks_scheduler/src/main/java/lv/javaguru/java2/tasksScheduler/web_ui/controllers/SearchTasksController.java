package lv.javaguru.java2.tasksScheduler.web_ui.controllers;

import lv.javaguru.java2.tasksScheduler.core.requests.SearchTasksRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchTasksController {

    @GetMapping(value = "/searchTasks")
    public String searchTasks(ModelMap modelMap) {

        return "searchTasks";
    }
}
