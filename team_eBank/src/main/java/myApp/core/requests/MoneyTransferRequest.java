package myApp.core.requests;

public class MoneyTransferRequest {

    private String personalCode;
    private int value;
    private String anotherPersonalCode;

    public MoneyTransferRequest(String personalCode, String anotherPersonalCode, int value) {
        this.personalCode = personalCode;
        this.value = value;
        this.anotherPersonalCode = anotherPersonalCode;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public int getValue() {
        return value;
    }


    public String getAnotherPersonalCode() {
        return anotherPersonalCode;
    }

}
