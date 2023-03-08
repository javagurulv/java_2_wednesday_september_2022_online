package lv.javaguru.java2.tasksScheduler.web_ui.controllers.rest;

import lv.javaguru.java2.tasksScheduler.core.requests.GetUsersRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.ordering_paging.Ordering;
import lv.javaguru.java2.tasksScheduler.core.requests.ordering_paging.Paging;
import lv.javaguru.java2.tasksScheduler.core.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.core.responses.GetUsersResponse;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.GetUsersService;
import lv.javaguru.java2.tasksScheduler.enums.MenuType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/getUsersNames")
public class GetUsersNamesRESTController {

    @Autowired private GetUsersService getUsersService;

    @PostMapping(value="/", consumes = "application/json", produces = "application/json")
    ResponseEntity<List<Map<String,String>>> getAllUserNames(@RequestBody Map<String,String> viewParam) {

        Ordering ordering = new Ordering("username", viewParam.get("ordering"));
        Paging paging = new Paging(Integer.valueOf(viewParam.get("pageNumber")),
                                    Integer.valueOf(viewParam.get("pageSize")));
        GetUsersRequest request = new GetUsersRequest("","", ordering, paging);
        GetUsersResponse response = getUsersService.execute(request, MenuType.START);
        if (response.hasErrors()) {
            List<Map<String,String>> errorMapList = createErrorMapList(response);
            return new ResponseEntity<>(errorMapList, HttpStatus.BAD_REQUEST);
        }

        List<Map<String,String>> userNamesMapList = createUsernameMapList(response);

        return new ResponseEntity<>(userNamesMapList, HttpStatus.OK);
    }

    @PostMapping(value="/{name}", consumes = "application/json", produces = "application/json")
    ResponseEntity<List<Map<String,String>>> getUserNames(@RequestBody Map<String,String> viewParam,
                                                             @PathVariable String name) {
        Ordering ordering = new Ordering("username", viewParam.get("ordering"));
        Paging paging = new Paging(Integer.valueOf(viewParam.get("pageNumber")),
                Integer.valueOf(viewParam.get("pageSize")));
        GetUsersRequest request = new GetUsersRequest(name,"", ordering, paging);
        GetUsersResponse response = getUsersService.execute(request, MenuType.START);
        if (response.hasErrors()) {
            List<Map<String,String>> errorMapList = createErrorMapList(response);
            return new ResponseEntity<>(errorMapList, HttpStatus.BAD_REQUEST);
        }

        List<Map<String,String>> userNamesMapList = createUsernameMapList(response);

        return new ResponseEntity<>(userNamesMapList, HttpStatus.OK);
    }

    private List<Map<String,String>> createErrorMapList(GetUsersResponse response) {
        List<Map<String,String>> errorMapList = new ArrayList<>();
        List<CoreError> errors = response.getErrors();
        for (CoreError error : errors) {
            Map<String,String> errorMap = new HashMap<>();
            errorMap.put("error", error.getField());
            errorMap.put("msg", error.getMessage());
            errorMapList.add(errorMap);
        }
        return errorMapList;
    }

    private List<Map<String,String>> createUsernameMapList(GetUsersResponse response) {
        List<Map<String,String>> userNamesMapList = new ArrayList<>();
        for (String name : response.getUsersNames()) {
            Map<String,String> nameMap = new HashMap<>();
            nameMap.put("username", name);
            userNamesMapList.add(nameMap);
        }
        return userNamesMapList;
    }
}
