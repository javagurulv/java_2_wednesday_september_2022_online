package lv.javaguru.java2.tasksScheduler.web_ui.controllers;

import lv.javaguru.java2.tasksScheduler.core.requests.GetUsersRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.ordering_paging.Ordering;
import lv.javaguru.java2.tasksScheduler.core.requests.ordering_paging.Paging;
import lv.javaguru.java2.tasksScheduler.core.responses.GetUsersResponse;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.GetUsersService;
import lv.javaguru.java2.tasksScheduler.core.services.system.CheckLoggedUserService;
import lv.javaguru.java2.tasksScheduler.enums.MenuType;
import lv.javaguru.java2.tasksScheduler.utils.ValueChecking;
import lv.javaguru.java2.tasksScheduler.utils.WebUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class GetUsersController {
    @Value("${web.UI.paging.pagesize}")
    private String pageSize;

    @Autowired
    private GetUsersService getUsersService;
    @Autowired
    private CheckLoggedUserService checkLoggedUserService;

    @GetMapping(value = "/showUsers")
    public String showUsersRegisteredInSystem(ModelMap modelMap, HttpSession session) {
        if (!WebUI.checkIfUserIsLoggedIn(checkLoggedUserService, session.getId())) {
            return "errorNotLoggedIn";
        }
        GetUsersRequest request = new GetUsersRequest();
        request.setPaging(new Paging(1, getPageSize()));
        request.setOrdering(new Ordering("username", "ASCENDING"));
        GetUsersResponse response = getUsersService.execute(request, MenuType.ADMIN);
        modelMap.addAttribute("request", request);
        if (response == null || response.getUsers() == null) {
            modelMap.addAttribute("users", null);
        } else {
            modelMap.addAttribute("users", response.getUsers());
        }

        modelMap.addAttribute("grid_msg", getGridMessage(response));
        HashMap<String, Integer> totals = getRecordsTotalsInfo(request);
        modelMap.addAttribute("currentPage", request.getPaging().getPageNumber());
        modelMap.addAttribute("totalItems", totals.get("totalItems"));
        modelMap.addAttribute("totalPages", totals.get("totalPages"));

        return "showUsers";
    }

    @PostMapping("/showUsers")
    public String processGetUsersRequest(@ModelAttribute(value = "request") GetUsersRequest request,
                                         @RequestParam(value="page", required=false, defaultValue = "") String page,
                                         @RequestParam(value="orderBy", required=false, defaultValue = "") String orderBy,
                                         @RequestParam(value="deleteId", required=false, defaultValue = "") String deleteId,
                                         ModelMap modelMap,
                                         HttpSession session) {
        if (!WebUI.checkIfUserIsLoggedIn(checkLoggedUserService, session.getId())) {
            return "errorNotLoggedIn";
        }
        normalizeRequest(page, orderBy, request);
        GetUsersResponse response = getUsersService.execute(request, MenuType.ADMIN);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("request", request);
            if (response.getUsers() == null) {
                modelMap.addAttribute("users", null);
            } else {
                modelMap.addAttribute("users", response.getUsers());
            }

            modelMap.addAttribute("grid_msg", getGridMessage(response));
            HashMap<String, Integer> totals = getRecordsTotalsInfo(request);
            modelMap.addAttribute("currentPage", request.getPaging().getPageNumber());
            modelMap.addAttribute("totalItems", totals.get("totalItems"));
            modelMap.addAttribute("totalPages", totals.get("totalPages"));
        }
        return "showUsers";
    }

    private Integer getPageSize() {
        if (ValueChecking.stringIsEmpty(pageSize) || !ValueChecking.stringIsInteger(pageSize)) {
            return 10;
        }
        return Integer.valueOf(pageSize);
    }

    private Integer getPageNumber(String page, GetUsersRequest request) {
        if (ValueChecking.stringIsInteger(page)) {
            return Integer.parseInt(page);
        }
        if (page.equalsIgnoreCase("next")) {
            return request.getPaging().getPageNumber() + 1;
        }
        if (page.equalsIgnoreCase("previous")) {
            return request.getPaging().getPageNumber() - 1;
        }
        return 1;
    }

    private void normalizeRequest(String page, String orderBy, GetUsersRequest request) {
        if (request.getOrdering() != null) {
            if (ValueChecking.stringIsEmpty(request.getOrdering().getOrderDirection())) {
                request.setOrdering(null);
            }
        }
        request.setPaging(new Paging(getPageNumber(page, request), getPageSize()));
        if (ValueChecking.stringIsEmpty(orderBy)) {
            request.setOrdering(new Ordering(request.getOrdering().getOrderBy(),
                    request.getOrdering().getOrderDirection()));
        } else {
            request.setOrdering(new Ordering(orderBy, request.getOrdering().getOrderDirection()
                    .equals("ASCENDING") ? "DESCENDING" : "ASCENDING"));
        }
    }

    private String getGridMessage(GetUsersResponse response) {
        if (response == null || response.getUsers() == null || response.getUsers().size() == 0) {
            return "No records to display";
        }
        return null;
    }

    private HashMap<String, Integer> getRecordsTotalsInfo(GetUsersRequest request) {
        HashMap<String, Integer> result = new HashMap<>();
        result.put("totalItems", 0);
        result.put("totalPages", 0);

        if (request == null ||
                request.getPaging() == null ||
                request.getPaging().getPageNumber() == null ||
                request.getPaging().getPageSize() == null ||
                request.getPaging().getPageSize() == 0) {
            return result;
        }

        GetUsersRequest allRecordsRequest = new GetUsersRequest(request.getUsername(),
                request.getEmail(), null, null);
        GetUsersResponse allRecordsResponse = getUsersService.execute(allRecordsRequest, MenuType.ADMIN);
        if (allRecordsResponse.hasErrors()) {
            return result;
        }
        int recordsCount = allRecordsResponse.getUsers().size();
        int pagesCount = recordsCount / request.getPaging().getPageSize();
        result.replace("totalItems", recordsCount);
        result.replace("totalPages", (recordsCount % request.getPaging().getPageSize() > 0) ? ++pagesCount : pagesCount);

        return result;
    }

}
