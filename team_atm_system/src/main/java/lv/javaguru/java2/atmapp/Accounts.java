package lv.javaguru.java2.atmapp;

import java.util.Objects;

class Accounts {

    private final String name;

    private final int uuid;

    private int balance;

    public Accounts(String name, int uuid, int balance) {
        this.name = name;
        this.uuid = uuid;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public int getUuid() {
        return uuid;
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
        return uuid == accounts.uuid && balance == accounts.balance && Objects.equals(name, accounts.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, uuid, balance);
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "name='" + name + '\'' +
                ", bornYear=" + uuid +
                ", balance=" + balance + "$" +
                '}';
    }
}
