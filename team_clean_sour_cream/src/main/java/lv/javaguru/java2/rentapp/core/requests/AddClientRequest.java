package lv.javaguru.java2.rentapp.core.requests;

import lombok.Getter;

@Getter
public class AddClientRequest {

    private String personalId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

}
