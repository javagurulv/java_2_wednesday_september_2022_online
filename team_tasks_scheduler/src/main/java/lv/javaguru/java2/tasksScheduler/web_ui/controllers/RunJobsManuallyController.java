package lv.javaguru.java2.tasksScheduler.web_ui.controllers;


import lv.javaguru.java2.tasksScheduler.core.requests.AmendSettingsRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.ChoiceRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.GetSettingsRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.JobRunRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.AmendSettingsResponse;
import lv.javaguru.java2.tasksScheduler.core.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.core.responses.GetSettingsResponse;
import lv.javaguru.java2.tasksScheduler.core.responses.JobRunResponse;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.AmendSettingsService;
import lv.javaguru.java2.tasksScheduler.core.services.scheduled_jobs.DueDatesUpdateService;
import lv.javaguru.java2.tasksScheduler.core.services.scheduled_jobs.RemindersSendingService;
import lv.javaguru.java2.tasksScheduler.core.services.scheduled_jobs.TasksCleanupService;
import lv.javaguru.java2.tasksScheduler.core.services.system.GetSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RunJobsManuallyController {

    @Autowired private TasksCleanupService tasksCleanupService;
    @Autowired private DueDatesUpdateService dueDatesUpdateService;
    @Autowired private RemindersSendingService remindersSendingService;

    DateTimeFormatter printFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    @GetMapping(value = "/runJobsManually")
    public String showSettingsAmendmentPage(ModelMap modelMap) {
        ChoiceRequest choiceRequest = new ChoiceRequest();
        modelMap.addAttribute("choiceRequest", choiceRequest);
        choiceRequest.setChoice("1");
        return "runJobsManually";
    }

    @PostMapping("/runJobsManually")
    public String processAmendmentRequest(@ModelAttribute(value = "choiceRequest") ChoiceRequest choiceRequest, ModelMap modelMap) {
        JobRunRequest request = new JobRunRequest(true);
        JobRunResponse response = null;
        switch (choiceRequest.getChoice()) {
            case "1":
                response = dueDatesUpdateService.execute(request);
                break;
            case "2":
                response = remindersSendingService.execute(request);
                break;
            case "3":
                response = tasksCleanupService.execute(request);
                break;
            default:
        }
        modelMap.addAttribute("result", getResultAsList(response));
        return "runJobsManually";
    }

    private List<String> getResultAsList(JobRunResponse response) {
        if (response == null) {
            return null;
        }
        List<String> result = new ArrayList<>();
        result.add("Job Name: " + response.getRunResult().getJobName());
        result.add("Started: " + response.getRunResult().getTimestampStart().format(printFormat));
        result.add("Ended: " + response.getRunResult().getTimestampEnd().format(printFormat));
        result.add("Items processed: " + response.getRunResult().getActionsCount());
        result.add("Completion status: " + response.getRunResult().getStatus());
        return result;
    }
}
