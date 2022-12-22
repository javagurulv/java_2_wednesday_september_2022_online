package myApp.core.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SearchBankAccountRequest {

    private String name;
    private String surname;
    private String personalCode;
    private Ordering order;
    private Paging paging;

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

    public SearchBankAccountRequest(String name, String surname, String personalCode, Paging paging) {
        this.name = name;
        this.surname = surname;
        this.personalCode = personalCode;
        this.paging = paging;
    }

    public SearchBankAccountRequest(String name, String surname, String personalCode, Ordering order, Paging paging) {
        this.name = name;
        this.surname = surname;
        this.personalCode = personalCode;
        this.order = order;
        this.paging = paging;
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
}