package lv.javaguru.java2.tasksScheduler.web_ui.controllers;

import lv.javaguru.java2.tasksScheduler.core.requests.ordering_paging.Ordering;
import lv.javaguru.java2.tasksScheduler.core.requests.ordering_paging.Paging;
import lv.javaguru.java2.tasksScheduler.enums.MenuType;
import lv.javaguru.java2.tasksScheduler.core.requests.GetUsersRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.GetUsersResponse;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.GetUsersService;
import lv.javaguru.java2.tasksScheduler.utils.ValueChecking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class GetUsersNamesController {
    @Autowired
    private GetUsersService getUsersService;

    @GetMapping(value = "/showUsernamesRegistered")
    public String showUsernamesRegisteredInSystem(ModelMap modelMap) {
        GetUsersRequest request = new GetUsersRequest();
        GetUsersResponse response = getUsersService.execute(request, MenuType.START);
        setDefaultRadioButtons(request);
        modelMap.addAttribute("request", request);
        modelMap.addAttribute("users", response.getUsersNames());
        modelMap.addAttribute("list_status", getListStatus(request, response));
        modelMap.addAttribute("list_pages", "(page: 1 of 1)");
        return "showUsernamesRegistered";
    }

    @PostMapping("/showUsernamesRegistered")
    public String processGetUsersRequest(@ModelAttribute(value = "request") GetUsersRequest request, ModelMap modelMap) {
        normalizeRequest(request);
        GetUsersResponse response = getUsersService.execute(request, MenuType.START);
        setDefaultRadioButtons(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("list_pages", getListPagesLabel(request));
            String listStatus = getListStatus(request, response);
            if (listStatus.equals("More records exist")) {
                request.getPaging().setPageNumber(request.getPaging().getPageNumber() + 1);
            }
            modelMap.addAttribute("request", request);
            modelMap.addAttribute("users", response.getUsersNames());
            modelMap.addAttribute("list_status", listStatus);
        }
        return "showUsernamesRegistered";
    }

    private void setDefaultRadioButtons(GetUsersRequest request) {
        if (request.getOrdering() == null) {
            request.setOrdering(new Ordering("", ""));
        }
    }

    private void normalizeRequest(GetUsersRequest request) {
        if (request.getOrdering() != null) {
            if (ValueChecking.stringIsEmpty(request.getOrdering().getOrderDirection())) {
                request.setOrdering(null);
            } else {
                request.getOrdering().setOrderBy("username");
            }
        }
        if (request.getPaging().getPageNumber() == null &&
                request.getPaging().getPageNumber() == null) {
            request.setPaging(null);
        }
    }

    private String getListStatus(GetUsersRequest request, GetUsersResponse response) {
        if (response == null || response.getUsersNames() == null || response.getUsersNames().isEmpty()) {
            return "No records to display";
        }
        if (!nextRecordExists(request)) {
            return "End of list";
        }
        return "More records exist";
    }

    private boolean nextRecordExists(GetUsersRequest request) {
        if (request == null ||
                request.getPaging() == null ||
                request.getPaging().getPageNumber() == null) {
            return false;
        }
        Integer pageNumber = request.getPaging().getPageNumber() + 1;
        Paging paging = new Paging(pageNumber, request.getPaging().getPageSize());
        GetUsersRequest nextRecordRequest = new GetUsersRequest(request.getUsername(),
                request.getEmail(), request.getOrdering(), paging);
        GetUsersResponse nextRecordResponse = getUsersService.execute(nextRecordRequest, MenuType.START);
        if (nextRecordResponse.hasErrors()) {
            return false;
        }
        if (nextRecordResponse.getUsersNames() == null || nextRecordResponse.getUsersNames().isEmpty()) {
            return false;
        }
        return true;
    }

    private String getListPagesLabel(GetUsersRequest request) {
        if (request == null ||
                request.getPaging() == null ||
                request.getPaging().getPageNumber() == null ||
                request.getPaging().getPageSize() == null ||
                request.getPaging().getPageSize() == 0) {
            return "(page: 1 of 1)";
        }
        String result = "(page:";
        GetUsersRequest allRecordsRequest = new GetUsersRequest(request.getUsername(),
                null, null, null);
        GetUsersResponse allRecordsResponse = getUsersService.execute(allRecordsRequest, MenuType.START);
        if (allRecordsResponse.hasErrors()) {
            return "";
        }
        int recordsCount = allRecordsResponse.getUsersNames().size();
        int pagesCount = recordsCount / request.getPaging().getPageSize();
        result = result + " " + request.getPaging().getPageNumber() + " of " +
                ((recordsCount % request.getPaging().getPageSize() > 0) ? ++pagesCount : pagesCount) + ")";
        return result;
    }
}
