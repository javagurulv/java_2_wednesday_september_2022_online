package generalPackage.core.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "transactions")
public class Transactions {

    @Column(name = "user_id")
    private int userID;

    @Id
    @Column (name = "transactionnumber")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transaction;

    @Column (name = "amount")
    private int amount;

    public Transactions() {
    }

        public Transactions(int userID, int amount) {
        this.userID = userID;
       this.amount = amount;
    }

    public int getUserID() {
        return userID;
    }

    public int getTransaction() {
        return transaction;
    }

    public int getAmount() {
        return amount;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setTransaction(int transaction) {
        this.transaction = transaction;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transactions that = (Transactions) o;
        return userID == that.userID && transaction == that.transaction && amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, transaction, amount);
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "userID=" + userID +
                ", transaction=" + transaction +
                ", amount=" + amount +
                '}';
    }
}
