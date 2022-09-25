package myApp.core.domain;

import java.util.Objects;

public class BankAccount {

    private String name;
    private String surname;
    private Long id;
    private String personalCode;
    private Roles role;
    private Account account;

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
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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
        return Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(id, that.id) && Objects.equals(personalCode, that.personalCode) && role == that.role && Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, id, personalCode, role, account);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", id=" + id +
                ", personalCode='" + personalCode + '\'' +
                ", role=" + role +
                ", account=" + account +
                '}';
    }
}
