package myApp.core.requests;

public class SearchBankAccountRequest {

    private String name;
    private String surname;
    private String personalCode;
    private Ordering order;

    public SearchBankAccountRequest(String name, String surname, String personalCode) {
        this.name = name;
        this.surname = surname;
        this.personalCode = personalCode;
    }

    public SearchBankAccountRequest(String name, String surname, String personalCode, Ordering order) {
        this.name = name;
        this.surname = surname;
        this.personalCode = personalCode;
        this.order = order;
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

    public Ordering getOrder() {
        return order;
    }
}
