package generalPackage.core.responses.usersResponses;

import java.util.List;

public class PrintBalanceResponse extends CoreResponseUsers {

    private int accountToFind;

    public PrintBalanceResponse(int accountToFind) {
        this.accountToFind = accountToFind;
    }

    public int getBalanceByID() {
        return accountToFind;
    }

    public PrintBalanceResponse(List<CoreErrorUsers> errors) {
        super(errors);
    }
}
