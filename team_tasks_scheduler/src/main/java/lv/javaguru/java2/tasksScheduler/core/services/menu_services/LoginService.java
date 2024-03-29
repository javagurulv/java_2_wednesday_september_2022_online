package lv.javaguru.java2.tasksScheduler.core.services.menu_services;


import lv.javaguru.java2.tasksScheduler.core.database.jpa.JpaTasksRepository;
import lv.javaguru.java2.tasksScheduler.core.database.jpa.JpaUsersRepository;
import lv.javaguru.java2.tasksScheduler.core.domain.Task;
import lv.javaguru.java2.tasksScheduler.core.domain.User;
import lv.javaguru.java2.tasksScheduler.core.requests.LoginRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.core.responses.LoginResponse;
import lv.javaguru.java2.tasksScheduler.core.services.system.SessionService;
import lv.javaguru.java2.tasksScheduler.core.services.validators.LoginValidator;
import lv.javaguru.java2.tasksScheduler.utils.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
@Transactional
public class LoginService {

    @Autowired private JpaUsersRepository usersRepository;
    @Autowired
    private LoginValidator validator;
    @Autowired private JpaTasksRepository tasksRepository;
    @Autowired private SessionService sessionService;

    public LoginResponse execute(LoginRequest request) {

        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new LoginResponse(errors);
        }

        User user = usersRepository.findUserByUsernameAndPassword(request.getUserName(),
                                             Encryption.stringHashing(request.getPassword()));

        if (request.getWebSessionId() == null) {
            sessionService.login(user.getId(), request.getPassword());
        }
        else {
            sessionService.webLogin(request.getWebSessionId(),user.getId(), request.getPassword());
        }
        tasksRepository.deleteByUserIdTillDate(user.getId(), LocalDateTime.now());
        updateDueDates(user.getId());
        return new LoginResponse(user);
    }

    private int updateDueDates(Long userId) {
        List<Task> tasks = tasksRepository.getAllTasksReadyForDueDateUpdate(userId);
        if (tasks == null) {
            return 0;
        }
        int recordsUpdated = 0;
        for (Task task : tasks) {
            do {
                task.setDueDate(task.getDueDate().plusDays(task.getRegularity()));
                if (task.getDueDate().isAfter(LocalDateTime.now().minusDays(1).with(LocalTime.MAX))) {
                    break;
                }
            } while(true);
            if (tasksRepository.update(task)) {
                recordsUpdated++;
            }
        }
        return recordsUpdated;
    }
}
