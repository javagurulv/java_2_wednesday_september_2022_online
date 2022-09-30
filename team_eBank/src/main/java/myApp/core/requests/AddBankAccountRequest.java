package myApp.core.requests;

public class AddBankAccountRequest {

    private String name;
    private String surname;
    private String personalCode;
    private String password;

    public AddBankAccountRequest(String name, String surname, String personalCode, String password) {
        this.name = name;
        this.surname = surname;
        this.personalCode = personalCode;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public String getPassword() {
        return password;
    }
}
