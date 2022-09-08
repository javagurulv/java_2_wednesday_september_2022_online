package myApp;

import java.util.Objects;

class BankAccount {

    private String name;
    private double balance;
    private int codeID;

    public BankAccount(String name, double balance , int codeID) {
        this.name = name;
        this.balance = balance;
        this.codeID = codeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getCodeID() {
        return codeID;
    }

    public void setCodeID(int codeID) {
        this.codeID = codeID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount account = (BankAccount) o;
        return Double.compare(account.balance, balance) == 0 && Objects.equals(name, account.name) && Objects.equals(codeID, account.codeID);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "name='" + name + '\'' +
                ", balance=" + balance +
                ", codeID=" + codeID +
                '}';
    }
}
