package lv.javaguru.java2.tasksScheduler.web_ui.controllers;


import lv.javaguru.java2.tasksScheduler.core.requests.AmendCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.AmendSettingsRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.GetCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.GetSettingsRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.*;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.AmendCurrentUserService;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.AmendSettingsService;
import lv.javaguru.java2.tasksScheduler.core.services.system.GetCurrentUserService;
import lv.javaguru.java2.tasksScheduler.core.services.system.GetSettingsService;
import lv.javaguru.java2.tasksScheduler.utils.WebUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SettingsAmendmentController {

    @Autowired
    private AmendSettingsService amendSettingsService;
    @Autowired private GetSettingsService getSettingsService;

    private List<CoreError> errors;

    @GetMapping(value = "/settingsAmendment")
    public String showSettingsAmendmentPage(ModelMap modelMap) {
        GetSettingsRequest request = new GetSettingsRequest(true);
        GetSettingsResponse response = getSettingsService.execute(request);
        if (response == null || response.getSettings() == null) {
            errors = new ArrayList<>();
            errors.add(new CoreError("Settings", "Are not set up."));
            modelMap.addAttribute("errors", errors);
        } else {
            modelMap.addAttribute("request", response.getSettings());
        }
        return "settingsAmendment";
    }

    @PostMapping("/settingsAmendment")
    public String processAmendmentRequest(@ModelAttribute(value = "request") AmendSettingsRequest request, ModelMap modelMap) {
        AmendSettingsResponse response = amendSettingsService.execute(request);
        if (response == null) {
            errors = new ArrayList<>();
            errors.add(new CoreError("Settings", "Nothing has been changed."));
            modelMap.addAttribute("errors", errors);
            return "settingsAmendment";
        }
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("succeed", response.getSettings());
        }
        return "settingsAmendment";
    }

}
