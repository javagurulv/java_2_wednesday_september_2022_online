package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.requests.MoneyTransferRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.MoneyTransferResponse;

import java.util.List;

public class MoneyTransferService {

    private DataBase dataBase;
    private MoneyTransferValidator validator;

    public MoneyTransferService(DataBase dataBase, MoneyTransferValidator validator) {
        this.dataBase = dataBase;
        this.validator = validator;
    }

    public MoneyTransferResponse execute(MoneyTransferRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            boolean result = dataBase.bankTransfer(request.getUserID(), request.getValue(), request.getAnotherAccountID());
            return new MoneyTransferResponse(result);
        } else {
            return new MoneyTransferResponse(errors);
        }

    }
}
