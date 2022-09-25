package lv.javaguru.java2.tasksScheduler.services;

import lv.javaguru.java2.tasksScheduler.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.utils.Encryption;

public class AmendCurrentUserService {

    private UsersRepository usersRepository;
    private SessionService sessionService;

    public AmendCurrentUserService(UsersRepository usersRepository, SessionService sessionService) {
        this.usersRepository = usersRepository;
        this.sessionService = sessionService;
    }

    public boolean execute(String username, String password, String email, String mobilePhone) {
        User currentUser = usersRepository.getUserById(sessionService.getCurrentUserId());
        if (currentUser == null)
            return false;
        User amendedUser = new User(username, Encryption.stringHashing(password), email, mobilePhone);
        amendedUser.setId(currentUser.getId());
        usersRepository.update(amendedUser);
        sessionService.setDecryptedPassword(password);
        return true;
    }
}
