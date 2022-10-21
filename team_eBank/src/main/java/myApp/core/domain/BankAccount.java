package myApp.core.domain;

import lombok.Data;


@Data
public class BankAccount {

    private String name;
    private String surname;
    private Long id;
    private Roles role;
    private String personalCode;
    private Account account;

    public BankAccount(String name, String surname, Roles role, String personalCode) {
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.personalCode = personalCode;
    }

    public BankAccount() {
    }
}
