package fitness_club.users.client;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public
class Client {
    private String name;
    private String surname;
    private int personalID;
    private String phoneNumber;
    private String email;
    private String gender;
    private int ID;
    private String password;
    private String passwordAgain;
    private String city;
    private String address;
}