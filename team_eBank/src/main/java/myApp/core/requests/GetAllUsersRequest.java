package myApp.core.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import myApp.core.domain.User;

import java.util.List;

@AllArgsConstructor
@Getter
public class GetAllUsersRequest {

    List<User> users;
}
