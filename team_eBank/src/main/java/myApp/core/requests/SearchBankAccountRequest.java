package myApp.core.requests;

public class SearchBankAccountRequest {

    private String name;
    private String surname;
    private String personalCode;

    public SearchBankAccountRequest(String name, String surname, String personalCode) {
        this.name = name;
        this.surname = surname;
        this.personalCode = personalCode;
    }

    public boolean nameNullCheck() {
        return this.name != null && !this.name.isBlank();
    }
    public boolean surnameNullCheck() {
        return this.surname != null && !this.surname.isBlank();
    }
    public boolean personalCodeNullCheck() {
        return this.personalCode != null && !this.personalCode.isBlank();
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
