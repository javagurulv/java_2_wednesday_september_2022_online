package myApp.core.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LogInRequest {

    private String login;
    private String password;
}
