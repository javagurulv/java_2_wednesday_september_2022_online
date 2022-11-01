package lv.javaguru.java2.atmapp;

import java.util.Objects;

public class Accounts {
    private String name;

    private int userID;

    private int balance;

    public Accounts(String name, int userID, int balance) {
        this.name = name;
        this.userID = userID;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }


    public int getUserID() {
        return userID;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accounts accounts = (Accounts) o;
        return Objects.equals(name, accounts.name) &&
                Objects.equals(userID, accounts.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, userID, balance);
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "name='" + name + '\'' +
                ", user ID=" + userID +
                ", balance=" + balance + "$" +
                '}';
    }
}
