package generalPackage.core.responses.usersResponses;

import java.util.List;

public class IncreaseBalanceResponse extends CoreResponseUsers{

    private boolean addMoneySuccessful;

    public IncreaseBalanceResponse(boolean addMoneySuccessful) {
        this.addMoneySuccessful = addMoneySuccessful;
    }

    public boolean isMoneyAdded() {
        return addMoneySuccessful;
    }

    public IncreaseBalanceResponse (List<CoreErrorUsers> errors){
        super(errors);
    }
}
