package myApp.core.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="bank_accounts")
public class BankAccount {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name="name", nullable = false)
    private String name;
    @Column(name="surname", nullable = false)
    private String surname;
    @Column(name="role", nullable = false)
    private String role;
    @Column(name="personal_code", nullable = false)
    private String personalCode;
    @Column(name="balance")
    private Integer balance;

    public BankAccount(String name, String surname, String role, String personalCode, Integer balance) {
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.personalCode = personalCode;
        this.balance = balance;
    }

    public BankAccount(User user, String name, String surname, String role, String personalCode) {
        this.user = user;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.personalCode = personalCode;
    }

    public BankAccount(String name, String surname, String role, String personalCode) {
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.personalCode = personalCode;
    }
}
