package generalPackage;

import java.util.Objects;

public class Accounts {
    private  String name;

    private  int userID;

    private int balance;

    public Accounts(String name, int userID, int balance) {
        this.name = name;
        this.userID = userID;
        this.balance = balance;
    }

    // NOT SURE IF IT IS NECESSARY!! :

//    public Accounts(String name, int userID) {
//        this.name = name;
//        this.userID = userID;
//    }

//    public Accounts(int userID) {
//        this.userID = userID;
//    }


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
                ", user ID=" + userID +
                ", balance=" + balance + "$" +
                '}';
    }
}
