package generalPackage.usersResponses;

public class IncreaseBalanceResponse {

    boolean addMoneySuccessful;

    public IncreaseBalanceResponse(boolean addMoneyConfirmation) {
        addMoneyConfirmation = addMoneyConfirmation;
    }

    public boolean addMoneySuccessful() {
        return addMoneySuccessful;
    }
}
