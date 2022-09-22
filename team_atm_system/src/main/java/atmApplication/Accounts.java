package atmApplication;

import java.util.Objects;

public class Accounts {
    private final String name;

    private final int userID;

    private int balance;

    public Accounts(String name, int userID, int balance) {
        this.name = name;
        this.userID = userID;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

//    public int getUserID(int userID) {
//        return userID;
//    }

    public int getUserID() {
        return userID;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance)  {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accounts accounts = (Accounts) o;
        return Objects.equals(name, accounts.name) &&
                Objects.equals(userID, accounts.userID);
        //userID == accounts.userID && balance == accounts.balance && Objects.equals(name, accounts.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, userID, balance);
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "name='" + name + '\'' +
                ", bornYear=" + userID +
                ", balance=" + balance + "$" +
                '}';
    }
}
