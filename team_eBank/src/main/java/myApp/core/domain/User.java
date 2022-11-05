package myApp.core.domain;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="login", nullable = false)
    private String personalCode;
    @Column(name="password", nullable = false)
    private String password;

    public User(String personalCode, String password) {
        this.personalCode = personalCode;
        this.password = password;
    }
}