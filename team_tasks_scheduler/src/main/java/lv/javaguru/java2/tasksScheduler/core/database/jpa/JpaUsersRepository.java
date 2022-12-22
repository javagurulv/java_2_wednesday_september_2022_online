package lv.javaguru.java2.tasksScheduler.core.database.jpa;

import lv.javaguru.java2.tasksScheduler.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaUsersRepository extends JpaRepository<User, Long>, JpaUsersCustomRep {

    User findUserByUsernameAndPassword(String username, String password);

    User findUserById(Long userId);

    boolean existsByUsername(String username);

    List<User> findAll();

    @Query(value = "SELECT * FROM users WHERE send_reminder = true", nativeQuery = true)
    List<User> getUsersAcceptedReminders();
}
