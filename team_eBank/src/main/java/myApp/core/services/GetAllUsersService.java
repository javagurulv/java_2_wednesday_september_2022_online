package myApp.core.services;

import myApp.core.database.jpa.JpaUserRepository;
import myApp.core.domain.User;
import myApp.core.requests.GetAllUsersRequest;
import myApp.core.responses.GetAllUsersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class GetAllUsersService {
    @Autowired
    private JpaUserRepository userRepository;

    public GetAllUsersResponse execute(GetAllUsersRequest request) {
        List<User> users = (userRepository.findAll());
        return new GetAllUsersResponse(null, users);
    }
}
