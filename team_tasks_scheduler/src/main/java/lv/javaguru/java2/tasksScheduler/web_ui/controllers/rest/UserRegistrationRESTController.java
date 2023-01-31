package lv.javaguru.java2.tasksScheduler.web_ui.controllers.rest;


import lv.javaguru.java2.tasksScheduler.core.requests.UserRegistrationRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.core.responses.UserRegistrationResponse;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/registerUser")
public class UserRegistrationRESTController {

    @Autowired private UserRegistrationService userRegistrationService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<List<Map<String,String>>> processRegistrationRequest(@RequestBody Map<String,String> userInfo) {

        boolean sendReminders = false;
        if (userInfo.get("sendReminders") != null) {
            if (userInfo.get("sendReminders").equals("on")) {
                sendReminders = true;
            }
        }
        UserRegistrationRequest request = new UserRegistrationRequest(userInfo.get("username"),
                                                                    userInfo.get("userPassword"),
                                                                    userInfo.get("userEmail"),
                                                                                sendReminders);
        UserRegistrationResponse response = userRegistrationService.execute(request);
        if (response.hasErrors()) {
            List<Map<String,String>> errorMapList = createErrorMapList(response);
            return new ResponseEntity<>(errorMapList, HttpStatus.BAD_REQUEST);
        }

        Map<String,String> responseMap = new HashMap<>();
        responseMap.put("message", "User created successfully");
        List<Map<String,String>> responseMapList = new ArrayList<>();
        responseMapList.add(responseMap);

        return new ResponseEntity<>(responseMapList, HttpStatus.OK);
    }

    private List<Map<String,String>> createErrorMapList(UserRegistrationResponse response) {
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
}
