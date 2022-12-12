package generalPackage.core.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "accounts")
public class Accounts {
    @Column(name = "name", nullable = false)
    private String name;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    @Column (name = "balance", nullable = false)
    private int balance;

    public Accounts() {
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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
