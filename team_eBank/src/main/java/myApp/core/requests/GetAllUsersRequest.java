package myApp.core.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import myApp.core.domain.User;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetAllUsersRequest {

    List<User> users;
}
