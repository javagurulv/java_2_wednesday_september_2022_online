package myApp.core.services;

import myApp.core.database.UserRepository;
import myApp.core.domain.User;
import myApp.core.requests.GetAllBankAccountsRequest;
import myApp.core.responses.GetAllUsersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class GetAllUsersService {
    @Autowired
    private UserRepository userRepository;

    public GetAllUsersResponse execute(GetAllBankAccountsRequest request) {
        List<User> users = (userRepository.getAllUsers());
        return new GetAllUsersResponse(null, users);
    }
}
