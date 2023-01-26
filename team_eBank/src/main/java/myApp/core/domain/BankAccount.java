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
    @Column(name="name", nullable = false)
    private String name;
    @Column(name="surname", nullable = false)
    private String surname;
    @Column(name="personal_code", nullable = false)
    private String personalCode;
    @Column(name="balance")
    private Integer balance;

    @Column(name="money_debt")
    private Integer debt;


    public BankAccount(String name, String surname, String personalCode, Integer balance) {
        this.name = name;
        this.surname = surname;
        this.personalCode = personalCode;
        this.balance = balance;
    }

    public BankAccount(String name, String surname, String personalCode) {
        this.name = name;
        this.surname = surname;
        this.personalCode = personalCode;
    }
}
