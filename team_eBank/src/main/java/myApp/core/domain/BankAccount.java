package myApp.core.domain;

import lombok.Data;


@Data
public class BankAccount {

    private String name;
    private String surname;
    private Long id;
    private String password;
    private Roles role;
    private String personalCode;
    private Account account;

    public BankAccount(String name, String surname, String password, Roles role, String personalCode) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.role = role;
        this.personalCode = personalCode;
    }
}
