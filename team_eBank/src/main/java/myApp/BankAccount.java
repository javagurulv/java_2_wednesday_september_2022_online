package myApp;

import java.util.Objects;

public class BankAccount {

    private String name;
    private String surname;
    private int balance;
    private Long id;

    public BankAccount(String name, String surname, int balance) {
        this.name = name;
        this.surname = surname;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return Double.compare(that.balance, balance) == 0 && Objects.equals(name, that.name) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, balance, id);
    }

    @Override
    public String toString() {
        return "BankAccount: " +
                "name = '" + name + '\'' +
                ", surname = '" + surname + '\'' +
                ", balance = " + balance +
                ", id = " + id +
                ' ';
    }
}
