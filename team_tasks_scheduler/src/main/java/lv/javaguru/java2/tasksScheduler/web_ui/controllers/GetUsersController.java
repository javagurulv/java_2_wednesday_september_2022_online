package lv.javaguru.java2.tasksScheduler.web_ui.controllers;

import lv.javaguru.java2.tasksScheduler.core.requests.GetUsersRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.ordering_paging.Ordering;
import lv.javaguru.java2.tasksScheduler.core.requests.ordering_paging.Paging;
import lv.javaguru.java2.tasksScheduler.core.responses.GetUsersResponse;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.GetUsersService;
import lv.javaguru.java2.tasksScheduler.enums.MenuType;
import lv.javaguru.java2.tasksScheduler.utils.ValueChecking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
public class GetUsersController {
    @Value("${web.UI.paging.pagesize}")
    private String pageSize;

    @Autowired
    private GetUsersService getUsersService;

    @GetMapping(value = "/showUsers")
    public String showUsersRegisteredInSystem(ModelMap modelMap) {
        GetUsersRequest request = new GetUsersRequest();
        request.setOrdering(new Ordering("username", "ASCENDING"));
        request.setPaging(new Paging(1, getPageSize()));
        GetUsersResponse response = getUsersService.execute(request, MenuType.ADMIN);
        if (response == null || response.getUsers() == null) {
            modelMap.addAttribute("users", null);
        } else {
            modelMap.addAttribute("users", response.getUsers());
        }
        modelMap.addAttribute("username", "");
        modelMap.addAttribute("email", "");
        modelMap.addAttribute("grid_msg", getGridMessage(response));
        HashMap<String, Integer> totals = getRecordsTotalsInfo(request);
        modelMap.addAttribute("currentPage", request.getPaging().getPageNumber());
        modelMap.addAttribute("totalItems", totals.get("totalItems"));
        modelMap.addAttribute("totalPages", totals.get("totalPages"));
        modelMap.addAttribute("orderBy", "username");
        modelMap.addAttribute("orderDir", "ASCENDING");
        modelMap.addAttribute("reverseOrderDir", "DESCENDING");
        return "showUsers";
    }

    @GetMapping("/showUsers/page/{pageNo}")
    public String showUsersRegisteredInSystemPaginated(@PathVariable (value = "pageNo") Integer pageNo,
                                                       @RequestParam("orderBy") String orderBy,
                                                       @RequestParam("orderDir") String orderDir,
                                                       @RequestParam("username") String username,
                                                       @RequestParam("email") String email,
                                                       ModelMap modelMap) {
        GetUsersRequest request = new GetUsersRequest();
        request.setOrdering(new Ordering(orderBy, orderDir));
        request.setPaging(new Paging(pageNo, getPageSize()));
        request.setUsername(username);
        request.setEmail(email);
        GetUsersResponse response = getUsersService.execute(request, MenuType.ADMIN);
        if (response != null ) {
            if (response.hasErrors()) {
                modelMap.addAttribute("errors", response.getErrors());
            } else {
                if (response.getUsers() == null) {
                    modelMap.addAttribute("users", null);
                } else {
                    modelMap.addAttribute("users", response.getUsers());
                }
            }
        }
        modelMap.addAttribute("username", request.getUsername());
        modelMap.addAttribute("email", request.getEmail());
        modelMap.addAttribute("grid_msg", getGridMessage(response));
        HashMap<String, Integer> totals = getRecordsTotalsInfo(request);
        modelMap.addAttribute("currentPage", request.getPaging().getPageNumber());
        modelMap.addAttribute("totalItems", totals.get("totalItems"));
        modelMap.addAttribute("totalPages", totals.get("totalPages"));
        modelMap.addAttribute("orderBy", orderBy);
        modelMap.addAttribute("orderDir", orderDir);
        modelMap.addAttribute("reverseOrderDir", orderDir.equals("ASCENDING") ? "DESCENDING" : "ASCENDING");
        return "showUsers";
    }

    private Integer getPageSize() {
        if (ValueChecking.stringIsEmpty(pageSize) || !ValueChecking.stringIsInteger(pageSize)) {
            return 10;
        }
        return Integer.valueOf(pageSize);
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
