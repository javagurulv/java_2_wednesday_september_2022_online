package lv.javaguru.java2.tasksScheduler.core.database.jpa;

import lv.javaguru.java2.tasksScheduler.core.domain.User;

import java.util.List;

public interface JpaUsersCustomRep {

    boolean update(User user);

    List<User> getUsersByUsername(String username);

    List<User> getUsersByEmail(String email);

    List<User> getUsersByUsernameAndEmail(String username, String email);

}
