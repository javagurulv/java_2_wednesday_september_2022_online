package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.domain.BankAccount;
import myApp.core.requests.LogInRequest;
import myApp.core.responses.LogInResponse;

import java.util.Optional;

public class LogInService {

    private DataBase dataBase;
    private UserService userService;

    public LogInService(DataBase dataBase, UserService userService) {
        this.dataBase = dataBase;
        this.userService = userService;
    }

    public LogInResponse execute(LogInRequest request) {
        Optional<BankAccount> result = dataBase.getAllBankAccounts().stream()
                .filter(bankAccount -> bankAccount.getPersonalCode().equals(request.getPersonalCode()))
                .filter(bankAccount -> bankAccount.getPassword().equals(request.getPassword()))
                .findAny();
        if (result.isPresent()) {
            return new LogInResponse(userService.logIn(request.getPersonalCode(), request.getPassword()));
        }
        return new LogInResponse("Error");
    }

}
