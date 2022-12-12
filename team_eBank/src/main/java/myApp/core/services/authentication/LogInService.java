package myApp.core.services.authentication;

import myApp.core.database.BankAccountRepository;
import myApp.core.domain.BankAccount;
import myApp.core.requests.LogInRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.LogInResponse;
import myApp.core.services.validators.LogInValidator;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

//@Componentomponent
//@Transactionalnsactional
public class LogInService {

    @Autowired
    private UserService userService;
    @Autowired
    private LogInValidator validator;
    @Autowired
    private BankAccountRepository bankAccountRepository;

    public LogInResponse execute(LogInRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new LogInResponse(errors);
        }
        String encodePassword = Base64.getEncoder()
                .encodeToString(request.getPassword()
                        .getBytes(StandardCharsets.UTF_8));
        String encodeLogin = Base64.getEncoder()
                .encodeToString(request.getLogin()
                        .getBytes(StandardCharsets.UTF_8));
        if (userService.logIn(encodeLogin, encodePassword)) {
            BankAccount bankAccount = bankAccountRepository.getById(userService.getId());
            if (bankAccount != null) {
                userService.setPersonalCode(bankAccount.getPersonalCode());
                return new LogInResponse(bankAccount.getPersonalCode());
            }
        }
        return new LogInResponse((String) null);
    }
}