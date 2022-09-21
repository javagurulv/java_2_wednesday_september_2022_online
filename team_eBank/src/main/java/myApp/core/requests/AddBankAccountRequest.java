package myApp.core.requests;

public class AddBankAccountRequest {

    private String name;
    private String surname;
    private String personalCode;

    public AddBankAccountRequest(String name, String surname, String personalCode) {
        this.name = name;
        this.surname = surname;
        this.personalCode = personalCode;
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
}
