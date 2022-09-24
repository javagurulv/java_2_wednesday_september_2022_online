package myApp;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class BankAccount {

    private String name;
    private String surname;
    private Long id;
    private String personalCode;
    private Roles role;
    private Account accounts;

    public BankAccount(String name, String surname, Roles role, String personalCode) {
        this.personalCode = personalCode;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Roles getRoles() {
        return role;
    }

    public void setRoles(Roles roles) {
        this.role = roles;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Account getAccounts() {
        return accounts;
    }

    public void setAccounts(Account accounts) {
        this.accounts = accounts;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(id, that.id) && Objects.equals(personalCode, that.personalCode) && role == that.role && Objects.equals(accounts, that.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, id, personalCode, role, accounts);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", id=" + id +
                ", personalCode='" + personalCode + '\'' +
                ", role=" + role +
                ", accounts=" + accounts +
                '}';
    }
}
