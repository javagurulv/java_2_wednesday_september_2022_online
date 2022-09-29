package myApp.core.domain;

public class Account {

    private Long id;
     private int  balance;

    public Account(Long id, int balance) {
        this.id = id;
        this.balance = balance;
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

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                '}';
    }
}
