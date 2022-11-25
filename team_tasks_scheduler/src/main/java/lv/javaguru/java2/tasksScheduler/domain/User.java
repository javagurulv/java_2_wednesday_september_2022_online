package lv.javaguru.java2.tasksScheduler.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "id", referencedColumnName = "user_id")  //TODO foreign key re-check
    private Long id;
    @Column(name="username", nullable = false)
    private String username;
    @Column(name="user_password", nullable = false)
    private String password;
    @Column(name="email", nullable = false)
    private String email;
    @Column(name="send_reminder", nullable = false)
    private boolean sendReminders;

    public User() {
    }
    public User(String username, String password, String email, boolean sendReminders) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.sendReminders = sendReminders;
    }

    public User(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.sendReminders = user.isSendReminders();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSendReminders() {
        return this.sendReminders;
    }

    public boolean getSendReminders() {return this.sendReminders; }

    public void setSendReminders(boolean sendReminders) {
        this.sendReminders = sendReminders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return sendReminders == user.sendReminders && Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, sendReminders);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", sendReminders=" + sendReminders +
                '}';
    }
}
