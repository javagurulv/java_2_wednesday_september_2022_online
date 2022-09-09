package myApp.core.requests;

public class AddBankAccountRequest {

    private String name;
    private String surname;
    private int balance;

    public AddBankAccountRequest(String name, String surname, int balance) {
        this.name = name;
        this.surname = surname;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getBalance() {
        return balance;
    }
}
